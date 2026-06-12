package template.payment.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import template.payment.domain.type.PaymentMethod;
import template.payment.domain.type.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Payment {

    private final UUID id;
    private final UUID orderId;
    private final BigDecimal amount;
    private final PaymentStatus status;
    private final PaymentMethod method;
    private final Instant createdAt;
}
