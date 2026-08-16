package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {

    ASSIGNED("Assigned"),
    SENT("Sent"),
    DELIVERED("Delivered");

    private final String description;

    public static DeliveryStatus fromString(final String value) {
        for (final DeliveryStatus status : DeliveryStatus.values()) {
            if (status.description.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Impossible to parse delivery status type from: %s", value));
    }
}
