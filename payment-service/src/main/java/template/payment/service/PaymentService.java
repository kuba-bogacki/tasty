package template.payment.service;

import common.events.order.OrderCreatedEvent;

public interface PaymentService {

    void processPayment(OrderCreatedEvent event);
}
