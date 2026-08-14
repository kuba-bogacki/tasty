package template.courier.publisher;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.courier.domain.dto.DeliveryDto;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultCourierEventPublisher implements CourierEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishDeliveryAssigned(DeliveryDto.Assigned assignedDelivery) {
        final DeliveryAssignedEvent event = DeliveryAssignedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .deliveryId(assignedDelivery.id())
                .orderId(assignedDelivery.orderId())
                .courierId(assignedDelivery.courierId())
                .build();

        kafkaTemplate.send(Topics.DELIVERY_ASSIGNED, assignedDelivery.id().toString(), event);
        log.info("Event 'delivery assigned' with id: {} successfully published.", event.eventId());
    }
}
