package template.courier.subscriber;

import common.events.order.OrderPreparingEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface CourierEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_PREPARING, groupId = Topics.TEMPLATE_COURIER_SERVICE)
    void subscribeOrderPreparing(OrderPreparingEvent event);
}
