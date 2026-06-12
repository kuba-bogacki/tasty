package template.payment.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentMethod {

    CARD("Card"),
    BLIK("Blik"),
    CASH("Cash");

    private final String description;
}
