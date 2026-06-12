package common.events.payment;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentCompletedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID paymentId,
        @NotBlank UUID orderId,
        @NotBlank BigDecimal amount

) implements DomainEvent {}