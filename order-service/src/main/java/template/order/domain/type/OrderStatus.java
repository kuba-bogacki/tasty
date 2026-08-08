package template.order.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    CREATED("Created"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PREPARING("Preparing"),
    CANCELLED("Cancelled"),
    READY_FOR_PICKUP("Ready for pickup"),
    IN_DELIVERY("In delivery"),
    DELIVERED("Delivered");

    private final String description;
}
