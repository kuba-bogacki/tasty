package template.notification.service;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderCancelledEvent;
import common.events.order.OrderReadyEvent;
import common.events.payment.PaymentRefundedEvent;

public interface NotificationService {

    // Events handlers
    void handleOrderAccepted(OrderAcceptedEvent event);
    void handleOrderCancelled(OrderCancelledEvent event);
    void handleOrderReady(OrderReadyEvent event);
    void handlePaymentRefunded(PaymentRefundedEvent event);
    void handleDeliveryAssigned(DeliveryAssignedEvent event);
}
