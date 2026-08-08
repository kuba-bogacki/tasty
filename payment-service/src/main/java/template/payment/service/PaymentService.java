package template.payment.service;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;

public interface PaymentService {

    void processPayment(OrderCreatedEvent event);
    void processRefund(OrderRejectedEvent event);
}
