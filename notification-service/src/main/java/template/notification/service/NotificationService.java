package template.notification.service;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.order.*;
import common.events.payment.PaymentRefundedEvent;

public interface NotificationService {

    // Events handlers
    void handleOrderAccepted(OrderAcceptedEvent event);
    void handleOrderCancelled(OrderCancelledEvent event);
    void handleOrderPrepared(OrderPreparedEvent event);
    void handlePaymentRefunded(PaymentRefundedEvent event);
    void handleDeliveryAssigned(DeliveryAssignedEvent event);
    void handleOrderSent(OrderSentEvent event);
    void handleOrderDelivered(OrderDeliveredEvent event);
}
