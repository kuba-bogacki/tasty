package common.events.deliver;

import common.events.DomainEvent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record CourierAssignedEvent(

        @NotBlank UUID eventId,
        @NotBlank Instant occurredAt,
        @NotBlank UUID orderId,
        @NotBlank UUID courierId

) implements DomainEvent {}
