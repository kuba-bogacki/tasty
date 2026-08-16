package template.order.service;

import common.events.delivery.DeliverySentEvent;
import common.events.payment.PaymentFailedEvent;
import common.events.preparation.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import template.order.domain.DeliveryAddress;
import template.order.domain.Order;
import template.order.domain.OrderItem;
import template.order.domain.dto.DeliveryAddressDto;
import template.order.domain.dto.OrderDto;
import template.order.domain.dto.OrderItemDto;
import template.order.domain.type.OrderStatus;
import template.order.publisher.OrderEventPublisher;
import template.order.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public void createOrder(OrderDto.Create createDto) {
        final Order order = Order.builder()
                .customerId(createDto.customerId())
                .restaurantId(createDto.restaurantId())
                .totalAmount(getTotalAmount(createDto.items()))
                .deliveryAddress(getDeliveryAddress(createDto.deliveryAddress()))
                .status(OrderStatus.CREATED)
                .createdAt(Instant.now())
                .build();
        order.addItems(getOrderItemList(createDto.items()));

        final Order savedOrder = orderRepository.save(order);
        log.info("New order with id {} successfully saved.", savedOrder.getId());

        final OrderDto.Publish publishedOrder = OrderDto.Publish.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .paymentMethod(createDto.paymentMethod())
                .totalAmount(savedOrder.getTotalAmount())
                .build();
        orderEventPublisher.publishOrderCreated(publishedOrder);
    }

    @Override
    public void deliverOrder(OrderDto.Deliver createDto) {
        final Order order = findOrder(createDto.id());
        order.setStatus(OrderStatus.DELIVERED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Delivered' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Deliver deliverOrder = OrderDto.Deliver.builder()
                .id(savedOrder.getId())
                .courierId(createDto.courierId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderDelivered(deliverOrder);
    }

    @Override
    public void handlePreparationAccepted(PreparationAcceptedEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.ACCEPTED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Accepted' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Accept acceptedOrder = OrderDto.Accept.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderAccepted(acceptedOrder);
    }

    @Override
    @Transactional
    public void handlePreparationRejected(PreparationRejectedEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.REJECTED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Rejected' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Reject rejectedOrder = OrderDto.Reject.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .reason(event.reason())
                .build();
        orderEventPublisher.publishOrderRejected(rejectedOrder);
    }

    @Override
    public void handlePreparationWithdrew(PreparationWithdrewEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.WITHDREW);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Withdrew' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Withdraw withdrawOrder = OrderDto.Withdraw.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderWithdrew(withdrawOrder);
    }

    @Override
    public void handlePreparationStarted(PreparationStartedEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.STARTED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Started' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Start startOrder = OrderDto.Start.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderStarted(startOrder);
    }

    @Override
    public void handlePreparationCompleted(PreparationCompletedEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.PREPARED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Prepared' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Prepare orderPrepare = OrderDto.Prepare.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderPrepared(orderPrepare);
    }

    @Override
    public void handlePaymentFailed(PaymentFailedEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.CANCELLED);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Cancelled' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Cancel rejectedOrder = OrderDto.Cancel.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderCancelled(rejectedOrder);
    }

    @Override
    public void handleDeliverySent(DeliverySentEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.SENT);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Sent' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Send rejectedOrder = OrderDto.Send.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .build();
        orderEventPublisher.publishOrderSent(rejectedOrder);
    }

    private BigDecimal getTotalAmount(List<OrderItemDto> items) {
        return BigDecimal.valueOf(items.stream()
                .mapToDouble(OrderItemDto::unitPrice)
                .sum());
    }

    private DeliveryAddress getDeliveryAddress(DeliveryAddressDto deliveryAddressDto) {
        return DeliveryAddress.builder()
                .city(deliveryAddressDto.city())
                .postalCode(deliveryAddressDto.postalCode())
                .street(deliveryAddressDto.street())
                .buildingNumber(deliveryAddressDto.buildingNumber())
                .apartmentNumber(deliveryAddressDto.apartmentNumber())
                .build();
    }

    private List<OrderItem> getOrderItemList(List<OrderItemDto> orderItemDtoList) {
        return orderItemDtoList.stream()
                .map(this::getOrderItem)
                .toList();
    }

    private OrderItem getOrderItem(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .name(orderItemDto.name())
                .unitPrice(BigDecimal.valueOf(orderItemDto.unitPrice()))
                .build();
    }

    private Order findOrder(UUID orderId) {
        final Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find order with id equal: %s", orderId));
        }
        return order.get();
    }
}
