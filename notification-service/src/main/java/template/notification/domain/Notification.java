package template.notification.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import template.notification.domain.type.NotificationStatus;
import template.notification.domain.type.NotificationType;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Notification {

    private final UUID id;
    private final UUID recipientId;
    private final NotificationType type;
    private final String content;
    private final NotificationStatus status;
    private final Instant createdAt;
}
