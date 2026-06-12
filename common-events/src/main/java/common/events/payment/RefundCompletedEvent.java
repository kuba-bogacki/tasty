package common.events.payment;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public record RefundCompletedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID paymentId,
        @NotBlank UUID orderId

) implements DomainEvent {}
