package template.notification.service;

import common.events.payment.PaymentCompletedEvent;

public interface NotificationService {

    void sendPaymentNotify(PaymentCompletedEvent event);
}
