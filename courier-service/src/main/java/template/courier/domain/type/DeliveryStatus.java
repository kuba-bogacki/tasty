package template.courier.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {

    ASSIGNED("Assigned"),
    ON_THE_WAY("On the way"),
    DELIVERED("Delivered");

    private final String description;
}
