package template.order.subscriber;

import common.events.payment.PaymentFailedEvent;
import common.events.preparation.*;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface OrderEventSubscriber {

    @KafkaListener(topics = Topics.PREPARATION_ACCEPTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationAccepted(PreparationAcceptedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_REJECTED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationRejected(PreparationRejectedEvent event);

    @KafkaListener(topics = Topics.PREPARATION_WITHDRAW, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationWithdraw(PreparationWithdrawEvent event);

    @KafkaListener(topics = Topics.PREPARATION_IN_PROGRESS, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationInProgress(PreparationInProgressEvent event);

    @KafkaListener(topics = Topics.PREPARATION_READY, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePreparationReady(PreparationReadyEvent event);

    @KafkaListener(topics = Topics.PAYMENT_FAILED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribePaymentFailed(PaymentFailedEvent event);
}
