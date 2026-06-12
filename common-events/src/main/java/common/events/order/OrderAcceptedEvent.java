package common.events.order;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public record OrderAcceptedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID orderId,
        @NotBlank UUID restaurantId

) implements DomainEvent {}
