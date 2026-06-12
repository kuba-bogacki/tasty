package template.notification.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationStatus {

    PENDING("Pending"),
    SENT("Sent");

    private final String description;
}
