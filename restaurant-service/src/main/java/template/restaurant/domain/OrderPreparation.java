package template.restaurant.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import template.restaurant.domain.type.PreparationStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderPreparation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private UUID restaurantId;

    @Enumerated(EnumType.STRING)
    private PreparationStatus status;

    @Column(nullable = true)
    private Instant acceptedAt;
}
