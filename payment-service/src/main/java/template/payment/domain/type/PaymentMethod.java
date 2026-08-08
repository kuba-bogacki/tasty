package template.payment.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CARD("Card"),
    BLIK("Blik"),
    CASH("Cash");

    private final String description;

    public static PaymentMethod fromString(final String value) {
        for (final PaymentMethod status : PaymentMethod.values()) {
            if (status.description.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Impossible to parse payment method type from: %s", value));
    }
}
