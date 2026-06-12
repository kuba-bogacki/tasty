package template.order.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDto(

        @NotBlank String customerId,
        @NotBlank String restaurantId,
        @NotNull DeliveryAddressDto deliveryAddress,
        @NotEmpty List<OrderItemDto> items

) {}
