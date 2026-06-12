package common.events.deliver;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public record OrderPickedUpEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID orderId,
        @NotBlank UUID courierId

) implements DomainEvent {}
