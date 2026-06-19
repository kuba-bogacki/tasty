package template.notification.service;

import common.events.deliver.CourierAssignedEvent;
import common.events.payment.PaymentCompletedEvent;

public interface NotificationService {

    void sendPaymentNotify(PaymentCompletedEvent event);
    void sendCourierAssigned(CourierAssignedEvent event);
}
