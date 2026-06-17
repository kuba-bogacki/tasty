package template.courier.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import template.courier.domain.type.CourierStatus;
import template.courier.domain.type.DeliveryStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private UUID courierId;

    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(nullable = false)
    private Instant assignedAt;

    @Column(nullable = false)
    private Instant deliveredAt;
}
