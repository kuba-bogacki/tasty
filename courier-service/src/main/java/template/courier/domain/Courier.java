package template.courier.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import template.courier.domain.type.DeliveryStatus;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Courier {

    private final UUID id;
    private final UUID orderId;
    private final UUID courierId;
    private final DeliveryStatus status;
    private final Instant assignedAt;
    private final Instant deliveredAt;
}
