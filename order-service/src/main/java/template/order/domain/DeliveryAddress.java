package template.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {

    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
}
