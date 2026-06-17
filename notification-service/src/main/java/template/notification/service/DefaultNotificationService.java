package template.notification.service;

import common.events.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.notification.domain.Notification;
import template.notification.domain.type.NotificationStatus;
import template.notification.domain.type.NotificationType;
import template.notification.repository.NotificationRepository;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultNotificationService implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendPaymentNotify(PaymentCompletedEvent event) {
        final Notification notification = Notification.builder()
                .recipientId(UUID.randomUUID())
                .type(NotificationType.PUSH)
                .content("Payment successfully finalized")
                .status(NotificationStatus.SENT)
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }
}
