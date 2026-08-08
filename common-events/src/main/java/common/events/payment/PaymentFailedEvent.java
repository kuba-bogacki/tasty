package common.events.payment;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record PaymentFailedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID paymentId,
        @NotBlank UUID orderId,
        @NotBlank UUID customerId,
        @NotBlank String reason

) implements DomainEvent {}
