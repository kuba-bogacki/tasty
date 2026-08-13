package common.events.preparation;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record PreparationWithdrawEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID orderId,
        @NotBlank UUID customerId,
        @NotBlank UUID restaurantId

) implements DomainEvent {}
