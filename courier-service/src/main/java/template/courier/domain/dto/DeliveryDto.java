package template.courier.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

public class DeliveryDto {

    @Builder
    public record Assigned(

            @NotBlank UUID id,
            @NotBlank UUID orderId,
            @NotBlank UUID courierId

    ) {}

    @Builder
    public record Delivered(

            @NotBlank UUID id,
            @NotBlank UUID courierId,
            @NotBlank UUID status,
            @NotBlank String deliveredAt

    ) {}
}
