package template.order.subscriber;

import common.events.payment.RefundCompletedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface OrderEventSubscriber {

    @KafkaListener(topics = Topics.REFUND_COMPLETED, groupId = Topics.TEMPLATE_ORDER_SERVICE)
    void subscribeRefundCompleted(RefundCompletedEvent event);
}
