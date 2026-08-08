package common.events.topic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Topics {

    // Topics
    public static final String ORDER_CREATED = "order-created";
    public static final String ORDER_REJECTED = "order-rejected";
    public static final String ORDER_ACCEPTED = "order-accepted";
    public static final String ORDER_DELIVERED = "order-delivered";
    public static final String PAYMENT_COMPLETED = "payment-completed";
    public static final String PAYMENT_FAILED = "payment-failed";
    public static final String PAYMENT_REFUNDED = "payment-refunded";
    public static final String PREPARATION_ACCEPTED = "preparation-accepted";
    public static final String PREPARATION_REJECTED = "preparation-rejected";
    public static final String COURIER_ASSIGNED = "courier-assigned";

    // Group ids
    public static final String TEMPLATE_PAYMENT_SERVICE = "template-payment-service";
    public static final String TEMPLATE_NOTIFICATION_SERVICE = "template-notification-service";
    public static final String TEMPLATE_ORDER_SERVICE = "template-order-service";
    public static final String TEMPLATE_COURIER_SERVICE = "template-courier-service";
    public static final String TEMPLATE_RESTAURANT_SERVICE = "template-restaurant-service";
}
