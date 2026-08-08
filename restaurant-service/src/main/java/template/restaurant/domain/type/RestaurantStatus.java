package template.restaurant.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RestaurantStatus {

    OPEN("Open"),
    CLOSED("Closed");

    private final String description;

    public static RestaurantStatus fromString(final String value) {
        for (final RestaurantStatus status : RestaurantStatus.values()) {
            if (status.description.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Impossible to restaurant status type from: %s", value));
    }
}
