package template.notification.subscriber;

import common.events.deliver.CourierAssignedEvent;
import common.events.payment.PaymentCompletedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotificationEventSubscriber {

    @KafkaListener(topics = Topics.PAYMENT_COMPLETED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribePaymentCompleted(PaymentCompletedEvent event);

    @KafkaListener(topics = Topics.COURIER_ASSIGNED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribeCourierAssigned(CourierAssignedEvent event);
}
