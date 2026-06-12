package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RestaurantStatus {

    OPEN("Oper"),
    CLOSED("Closed");

    private final String description;
}
