package template.courier.domain;

import jakarta.persistence.*;
import lombok.*;
import template.courier.domain.type.CourierStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = true)
    private UUID orderId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    @Column(nullable = true)
    private Instant assignedAt;
}
