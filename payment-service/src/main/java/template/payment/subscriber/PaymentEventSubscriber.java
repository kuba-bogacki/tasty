package template.payment.subscriber;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface PaymentEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_CREATED, groupId = Topics.TEMPLATE_PAYMENT_SERVICE)
    void subscribeOrderCreated(OrderCreatedEvent event);

    @KafkaListener(topics = Topics.ORDER_REJECTED, groupId = Topics.TEMPLATE_PAYMENT_SERVICE)
    void subscribeOrderRejected(OrderRejectedEvent event);
}
