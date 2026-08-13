package template.notification.domain;

import jakarta.persistence.*;
import lombok.*;
import template.notification.domain.type.NotificationExternalType;
import template.notification.domain.type.NotificationStatus;
import template.notification.domain.type.NotificationType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID externalId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationExternalType externalType;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant createdAt;
}
