package template.order.subscriber;

import common.events.delivery.DeliverySentEvent;
import common.events.payment.PaymentFailedEvent;
import common.events.preparation.*;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface OrderEventSubscriber {

    @KafkaListener(topics = Topics.PREPARATION_ACCEPTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationAccepted(PreparationAcceptedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_REJECTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationRejected(PreparationRejectedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_WITHDREW, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationWithdrew(PreparationWithdrewEvent event);

    @KafkaListener(topics = Topics.PREPARATION_STARTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationStarted(PreparationStartedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_COMPLETED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationCompleted(PreparationCompletedEvent event);

    @KafkaListener(topics = Topics.PAYMENT_FAILED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePaymentFailed(PaymentFailedEvent event);

    @KafkaListener(topics = Topics.DELIVERY_SENT, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribeDeliverySent(DeliverySentEvent event);
}
