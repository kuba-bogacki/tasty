package template.notification.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
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
    private UUID recipientId;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(nullable = false)
    private Instant createdAt;
}
