package template.payment.subscriber;

import common.events.order.OrderCreatedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface PaymentEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_CREATED, groupId = Topics.TEMPLATE_PAYMENT_SERVICE)
    void subscribeOrderCreated(OrderCreatedEvent event);
}
