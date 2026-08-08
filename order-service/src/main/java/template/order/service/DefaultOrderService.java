package template.order.service;

import common.events.payment.RefundCompletedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
                .customerId(UUID.fromString(createDto.customerId()))
                .restaurantId(UUID.fromString(createDto.restaurantId()))
                .totalAmount(getTotalAmount(createDto.items()))
                .deliveryAddress(getDeliveryAddress(createDto.deliveryAddress()))
                .status(OrderStatus.CREATED)
                .createdAt(Instant.now())
                .build();
        order.addItems(getOrderItemList(createDto.items()));

        final Order savedOrder = orderRepository.save(order);
        final OrderDto.Publish publishedOrder = OrderDto.Publish.builder()
                .orderId(savedOrder.getId())
                .customerId(savedOrder.getCustomerId())
                .restaurantId(savedOrder.getRestaurantId())
                .paymentMethod(createDto.paymentMethod())
                .totalAmount(savedOrder.getTotalAmount())
                .build();
        log.info("New order with id {} successfully saved.", savedOrder.getId());
        orderEventPublisher.publishOrderCreated(publishedOrder);
    }

    @Override
    public void processCancel(RefundCompletedEvent event) {
        final Optional<Order> order = orderRepository.findById(event.orderId());
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find order by provided id: %s", event.orderId()));
        }
        order.get().setStatus(OrderStatus.CANCELLED);

        final Order savedOrder = orderRepository.save(order.get());
        log.info("Order with updated status and with id {} successfully saved.", savedOrder.getId());
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
}
