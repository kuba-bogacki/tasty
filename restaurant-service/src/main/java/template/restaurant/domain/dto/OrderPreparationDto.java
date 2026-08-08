package template.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderPreparationDto {

    @Builder
    public record Accepted(

            @NotBlank UUID orderId,
            @NotBlank UUID restaurantId

    ) {}

    @Builder
    public record Rejected(

            @NotBlank UUID orderId,
            @NotBlank UUID restaurantId,
            @NotBlank String reason

    ) {}
}
