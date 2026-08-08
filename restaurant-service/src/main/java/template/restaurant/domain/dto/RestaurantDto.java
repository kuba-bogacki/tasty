package template.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;

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
}
