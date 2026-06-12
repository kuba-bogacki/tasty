package template.order.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderItemDto(

        @NotBlank String name,
        @NotBlank Double unitPrice

) {}
