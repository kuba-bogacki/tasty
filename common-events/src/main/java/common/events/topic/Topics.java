package common.events.topic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Topics {

    public static final String ORDER_CREATED = "order-created";
    public static final String PAYMENT_SUCCEEDED = "payment-succeeded";
    public static final String ORDER_ACCEPTED = "order-accepted";
    public static final String COURIER_ASSIGNED = "courier-assigned";
    public static final String ORDER_DELIVERED = "order-delivered";
}
