package template.order.service;

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
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public void createOrder(OrderDto orderDto) {
        final Order order = Order.builder()
                .customerId(UUID.fromString(orderDto.customerId()))
                .restaurantId(UUID.fromString(orderDto.restaurantId()))
                .totalAmount(getTotalAmount(orderDto.items()))
                .deliveryAddress(getDeliveryAddress(orderDto.deliveryAddress()))
                .items(getOrderItemList(orderDto.items()))
                .status(OrderStatus.CREATED)
                .createdAt(Instant.now())
                .build();

        final Order savedOrder = orderRepository.save(order);
        orderEventPublisher.publishOrderCreated(savedOrder);
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
