package template.order.subscriber;

import common.events.payment.PaymentRefundedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationRejectedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface OrderEventSubscriber {

    @KafkaListener(topics = Topics.PREPARATION_ACCEPTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationAccepted(PreparationAcceptedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_REJECTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationRejected(PreparationRejectedEvent event);

    @KafkaListener(topics = Topics.PAYMENT_REFUNDED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribeRefundCompleted(PaymentRefundedEvent event);
}
