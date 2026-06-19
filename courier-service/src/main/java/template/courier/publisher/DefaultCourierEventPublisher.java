package template.courier.publisher;

import common.events.deliver.CourierAssignedEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.courier.domain.Delivery;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultCourierEventPublisher implements CourierEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishCourierAssigned(Delivery delivery) {
        final CourierAssignedEvent event = CourierAssignedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .courierId(delivery.getCourierId())
                .orderId(delivery.getOrderId())
                .build();

        kafkaTemplate.send(Topics.COURIER_ASSIGNED, delivery.getId().toString(), event);
        log.info("Event 'courier assigned' with id: {} successfully published.", event.eventId());
    }
}
