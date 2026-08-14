package template.order.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDto {

    @Builder
    public record Create(

            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId,
            @NotNull DeliveryAddressDto deliveryAddress,
            @NotEmpty List<OrderItemDto> items,
            @NotBlank String paymentMethod

    ) {}

    @Builder
    public record Publish(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId,
            @NotBlank String paymentMethod,
            @NotBlank BigDecimal totalAmount

    ) {}

    @Builder
    public record Accept(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId

    ) {}

    @Builder
    public record Reject(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId,
            @NotBlank String reason

    ) {}

    @Builder
    public record Cancel(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId,
            @NotBlank String reason

    ) {}

    @Builder
    public record Withdraw(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId

    ) {}

    @Builder
    public record Prepare(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId

    ) {}

    @Builder
    public record Ready(

            @NotBlank UUID id,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId

    ) {}
}
