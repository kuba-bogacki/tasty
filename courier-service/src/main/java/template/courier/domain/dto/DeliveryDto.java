package template.courier.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

public class DeliveryDto {

    @Builder
    public record Assign(

            @NotBlank UUID id,
            @NotBlank UUID orderId,
            @NotBlank UUID courierId

    ) {}

    @Builder
    public record Send(

            @NotBlank UUID id,
            @NotBlank UUID orderId

    ) {}

    @Builder
    public record Deliver(

            @NotBlank UUID id,
            @NotBlank UUID courierId,
            @NotBlank UUID status,
            @NotBlank String deliveredAt

    ) {}
}
