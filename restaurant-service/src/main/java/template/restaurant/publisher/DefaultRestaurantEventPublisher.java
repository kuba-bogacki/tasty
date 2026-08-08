package template.restaurant.publisher;

import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderRejectedEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.restaurant.domain.OrderPreparation;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultRestaurantEventPublisher implements RestaurantEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishOrderAccepted(OrderPreparation orderPreparation) {
        final OrderAcceptedEvent event = OrderAcceptedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(orderPreparation.getOrderId())
                .restaurantId(orderPreparation.getRestaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_ACCEPTED, orderPreparation.getId().toString(), event);
        log.info("Event 'order accepted' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderRejected(OrderPreparation orderPreparation) {
        final OrderRejectedEvent event = OrderRejectedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(orderPreparation.getOrderId())
                .restaurantId(orderPreparation.getRestaurantId())
                .reason("Restaurant is closed")
                .build();

        kafkaTemplate.send(Topics.ORDER_REJECTED, orderPreparation.getId().toString(), event);
        log.info("Event 'order rejected' with id: {} successfully published.", event.eventId());
    }
}
