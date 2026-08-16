package template.courier.subscriber;

import common.events.order.OrderDeliveredEvent;
import common.events.order.OrderStartedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface CourierEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_STARTED, groupId = Topics.TEMPLATE_COURIER_SERVICE)
    void subscribeOrderStarted(OrderStartedEvent event);

    @KafkaListener(topics = Topics.ORDER_DELIVERED, groupId = Topics.TEMPLATE_COURIER_SERVICE)
    void subscribeOrderDelivered(OrderDeliveredEvent event);
}
