package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {

    ASSIGNED("Assigned"),
    PICKED_UP("Picked up"),
    ON_THE_WAY("On the way"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String description;
}
