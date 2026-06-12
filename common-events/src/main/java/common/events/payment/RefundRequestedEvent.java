package common.events.payment;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record RefundRequestedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID paymentId,
        @NotBlank UUID orderId,
        @NotBlank BigDecimal amount

) implements DomainEvent {}
