package template.notification.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationType {

    EMAIL("Email"),
    SMS("Sms"),
    PUSH("Push");

    private final String description;
}
