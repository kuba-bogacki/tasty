package template.courier.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CourierDto(

        @NotBlank String name

) {}
