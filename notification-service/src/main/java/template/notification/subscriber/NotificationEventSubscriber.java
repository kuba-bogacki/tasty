package template.notification.subscriber;

import common.events.deliver.CourierAssignedEvent;
import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderCancelledEvent;
import common.events.payment.PaymentRefundedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotificationEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_ACCEPTED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribeOrderAccepted(OrderAcceptedEvent event);

    @KafkaListener(topics = Topics.ORDER_CANCELLED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribeOrderCancelled(OrderCancelledEvent event);

    @KafkaListener(topics = Topics.PAYMENT_REFUNDED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribePaymentRefunded(PaymentRefundedEvent event);

    @KafkaListener(topics = Topics.COURIER_ASSIGNED, groupId = Topics.TEMPLATE_NOTIFICATION_SERVICE)
    void subscribeCourierAssigned(CourierAssignedEvent event);
}
