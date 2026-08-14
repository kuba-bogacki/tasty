package template.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

public class RestaurantDto {

    public record Create(

            @NotBlank String name,
            @NotBlank String description,
            @NotBlank String status,
            @NotBlank String phoneNumber

    ) {}

    public record Update(

            @NotBlank String id,
            @NotBlank String status

    ) {}

    @Builder
    public record Withdraw(

            @NotBlank UUID orderId,
            @NotBlank UUID customerId,
            @NotBlank UUID restaurantId

    ) {}

    @Builder
    public record Prepare(

            @NotBlank UUID orderId,
            @NotBlank UUID restaurantId

    ) {}
}
