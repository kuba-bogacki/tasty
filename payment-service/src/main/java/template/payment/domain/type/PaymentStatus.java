package template.payment.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {

    PENDING("Pending"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    REFUNDED("Refunded");

    private final String description;
}
