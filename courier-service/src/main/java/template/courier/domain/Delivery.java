package template.courier.domain;

import jakarta.persistence.*;
import lombok.*;
import template.courier.domain.type.DeliveryStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private UUID courierId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(nullable = true)
    private Instant deliveredAt;
}
