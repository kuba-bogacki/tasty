package template.courier.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

public class CourierDto {

    @Builder
    public record Create(

            @NotBlank String name

    ) {}

    @Builder
    public record Status(

            @NotBlank UUID id,
            @NotBlank String status

    ) {}

    @Builder
    public record Find(

            @NotBlank UUID id,
            @NotBlank String name

    ) {}

    @Builder
    public record Assign(

            @NotBlank UUID id,
            @NotBlank UUID orderId

    ) {}
}
