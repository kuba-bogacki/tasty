package template.order.service;

import common.events.payment.PaymentRefundedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationRejectedEvent;
import template.order.domain.dto.OrderDto;

public interface OrderService {
    void createOrder(OrderDto.Create createDto);
    void acceptOrder(PreparationAcceptedEvent event);
    void rejectOrder(PreparationRejectedEvent event);
    void processCancel(PaymentRefundedEvent event);
}
