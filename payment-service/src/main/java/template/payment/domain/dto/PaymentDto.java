package template.payment.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import template.payment.domain.type.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDto {

    @Builder
    public record Process(

            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId,
            @NotBlank PaymentMethod method,
            @NotBlank BigDecimal totalAmount

    ) {}

    @Builder
    public record Completed(

            @NotBlank UUID paymentId,
            @NotBlank UUID restaurantId,
            @NotBlank UUID orderId,
            @NotBlank UUID customerId,
            @NotBlank BigDecimal amount

    ) {}

    @Builder
    public record Failed(

            @NotBlank UUID paymentId,
            @NotBlank UUID orderId,
            @NotBlank UUID customerId,
            @NotBlank String reason

    ) {}

    @Builder
    public record Refund(

            @NotBlank UUID paymentId,
            @NotBlank UUID orderId,
            @NotBlank UUID customerId

    ) {}
}
