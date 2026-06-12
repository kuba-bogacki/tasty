package template.order.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {

    CREATED("Created"),
    PAYMENT_PENDING("Payment pending"),
    PAID("Paid"),
    ACCEPTED("Accepted"),
    PREPARING("Preparing"),
    READY_FOR_PICKUP("Ready for pickup"),
    REJECTED("Rejected"),
    IN_DELIVERY("In delivery"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String description;
}
