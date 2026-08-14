package template.order.service;

import common.events.payment.PaymentFailedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationInProgressEvent;
import common.events.preparation.PreparationRejectedEvent;
import common.events.preparation.PreparationWithdrawEvent;
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
    public void handlePreparationWithdraw(PreparationWithdrawEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.WITHDRAW);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Withdraw' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Withdraw withdrawOrder = OrderDto.Withdraw.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderWithdraw(withdrawOrder);
    }

    @Override
    public void handlePreparationInProgress(PreparationInProgressEvent event) {
        final Order order = findOrder(event.orderId());
        order.setStatus(OrderStatus.PREPARING);

        final Order savedOrder = orderRepository.save(order);
        log.info("Order with 'Preparing' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Prepare prepareOrder = OrderDto.Prepare.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderPreparing(prepareOrder);
    }

    @Override
    public void handlePaymentFailed(PaymentFailedEvent event) {
        final Optional<Order> order = orderRepository.findById(event.orderId());
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find order to cancel with id equal: %s", event.orderId()));
        }
        order.get().setStatus(OrderStatus.CANCELLED);

        final Order savedOrder = orderRepository.save(order.get());
        log.info("Order with 'Cancelled' status and with id {} successfully updated.", savedOrder.getId());

        final OrderDto.Cancel rejectedOrder = OrderDto.Cancel.builder()
                .id(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .build();
        orderEventPublisher.publishOrderCancelled(rejectedOrder);
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
