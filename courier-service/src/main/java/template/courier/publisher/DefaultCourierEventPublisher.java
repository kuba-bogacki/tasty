package template.courier.publisher;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.delivery.DeliverySentEvent;
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
    public void publishDeliveryAssigned(DeliveryDto.Assign assignDelivery) {
        final DeliveryAssignedEvent event = DeliveryAssignedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .deliveryId(assignDelivery.id())
                .orderId(assignDelivery.orderId())
                .courierId(assignDelivery.courierId())
                .build();

        kafkaTemplate.send(Topics.DELIVERY_ASSIGNED, assignDelivery.id().toString(), event);
        log.info("Event 'delivery assigned' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishDeliverySent(DeliveryDto.Send sendDelivery) {
        final DeliverySentEvent event = DeliverySentEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .deliveryId(sendDelivery.id())
                .orderId(sendDelivery.orderId())
                .build();

        kafkaTemplate.send(Topics.DELIVERY_SENT, sendDelivery.id().toString(), event);
        log.info("Event 'delivery sent' with id: {} successfully published.", event.eventId());
    }
}
