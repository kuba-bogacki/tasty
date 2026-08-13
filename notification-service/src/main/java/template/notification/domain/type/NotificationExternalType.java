package template.notification.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationExternalType {

    CUSTOMER("Customer"),
    RESTAURANT("Restaurant"),
    DELIVERY("Delivery"),
    ORDER("Order");

    private final String description;
}
