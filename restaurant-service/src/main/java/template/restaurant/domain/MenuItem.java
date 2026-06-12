package template.restaurant.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class MenuItem {

    private final UUID id;
    private final UUID restaurantId;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean available;
}
