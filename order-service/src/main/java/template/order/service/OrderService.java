package template.order.service;

import common.events.payment.RefundCompletedEvent;
import template.order.domain.dto.OrderDto;

public interface OrderService {
    void createOrder(OrderDto.Create createDto);
    void processCancel(RefundCompletedEvent event);
}
