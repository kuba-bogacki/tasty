package template.payment.service;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
import common.events.order.OrderWithdrewEvent;

public interface PaymentService {

    // Events handlers
    void handleOrderCreated(OrderCreatedEvent event);
    void handleOrderRejected(OrderRejectedEvent event);
    void handleOrderWithdrew(OrderWithdrewEvent event);
}
