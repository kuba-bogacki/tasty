package common.events.order;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public record OrderDeliveredEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID orderId,
        @NotBlank UUID courierId

) implements DomainEvent {}
