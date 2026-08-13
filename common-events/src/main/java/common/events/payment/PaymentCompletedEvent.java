package common.events.payment;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record PaymentCompletedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID paymentId,
        @NotBlank UUID restaurantId,
        @NotBlank UUID orderId,
        @NotBlank UUID customerId,
        @NotBlank BigDecimal amount

) implements DomainEvent {}