package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourierStatus {

    AVAILABLE("Available"),
    BUSY("Busy");

    private final String description;

    public static CourierStatus fromString(final String value) {
        for (final CourierStatus status : CourierStatus.values()) {
            if (status.description.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Impossible to parse courier status type from: %s", value));
    }
}
