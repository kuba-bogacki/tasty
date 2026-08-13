package template.restaurant.publisher;

import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationRejectedEvent;
import common.events.preparation.PreparationWithdrawEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.restaurant.domain.dto.OrderPreparationDto;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultRestaurantEventPublisher implements RestaurantEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishPreparationAccepted(OrderPreparationDto.Accepted acceptedPreparation) {
        final PreparationAcceptedEvent event = PreparationAcceptedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(acceptedPreparation.orderId())
                .restaurantId(acceptedPreparation.restaurantId())
                .build();

        kafkaTemplate.send(Topics.PREPARATION_ACCEPTED, acceptedPreparation.orderId().toString(), event);
        log.info("Event 'preparation accepted' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishPreparationRejected(OrderPreparationDto.Rejected rejectedPreparation) {
        final PreparationRejectedEvent event = PreparationRejectedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(rejectedPreparation.orderId())
                .restaurantId(rejectedPreparation.restaurantId())
                .reason(rejectedPreparation.reason())
                .build();

        kafkaTemplate.send(Topics.PREPARATION_REJECTED, rejectedPreparation.orderId().toString(), event);
        log.info("Event 'preparation rejected' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishPreparationWithdraw(OrderPreparationDto.Withdraw withdrawPreparation) {
        final PreparationWithdrawEvent event = PreparationWithdrawEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(withdrawPreparation.orderId())
                .customerId(withdrawPreparation.customerId())
                .restaurantId(withdrawPreparation.restaurantId())
                .build();

        kafkaTemplate.send(Topics.PREPARATION_WITHDRAW, withdrawPreparation.orderId().toString(), event);
        log.info("Event 'preparation withdraw' with id: {} successfully published.", event.eventId());
    }
}
