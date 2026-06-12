package template.restaurant.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import template.restaurant.domain.type.RestaurantStatus;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Restaurant {

    private final UUID id;
    private final String name;
    private final String description;
    private final RestaurantStatus status;
    private final String phoneNumber;
    private final Instant createdAt;
}
