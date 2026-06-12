package template.order.service;

import template.order.domain.dto.OrderDto;

public interface OrderService {
    void createOrder(OrderDto orderDto);
}
