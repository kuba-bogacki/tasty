package template.order.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DeliveryAddressDto(

        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String street,
        @NotBlank String buildingNumber,
        @NotBlank String apartmentNumber

) {}
