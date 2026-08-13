package template.notification.service;

import common.events.deliver.CourierAssignedEvent;
import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderCancelledEvent;
import common.events.payment.PaymentRefundedEvent;

public interface NotificationService {

    // Events handlers
    void handleOrderAccepted(OrderAcceptedEvent event);
    void handleOrderCancelled(OrderCancelledEvent event);
    void handlePaymentRefunded(PaymentRefundedEvent event);
    void handleCourierAssigned(CourierAssignedEvent event);
}
