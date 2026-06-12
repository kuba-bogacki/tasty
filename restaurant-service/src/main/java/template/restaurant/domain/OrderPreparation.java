package template.restaurant.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import template.restaurant.domain.type.PreparationStatus;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class OrderPreparation {

    private final UUID id;
    private final UUID orderId;
    private final UUID restaurantId;
    private final PreparationStatus status;
    private final Instant acceptedAt;
}
