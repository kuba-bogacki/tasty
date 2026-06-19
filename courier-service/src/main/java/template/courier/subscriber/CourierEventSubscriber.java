package template.courier.subscriber;

import common.events.order.OrderAcceptedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface CourierEventSubscriber {

    @KafkaListener(topics = Topics.ORDER_ACCEPTED, groupId = Topics.TEMPLATE_COURIER_SERVICE)
    void subscribeOrderAccepted(OrderAcceptedEvent event);
}
