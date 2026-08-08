package template.restaurant.subscriber;

import common.events.payment.PaymentCompletedEvent;
import common.events.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;

public interface RestaurantEventSubscriber {

    @KafkaListener(topics = Topics.PAYMENT_COMPLETED, groupId = Topics.TEMPLATE_RESTAURANT_SERVICE)
    void subscribePaymentCompleted(PaymentCompletedEvent event);
}
