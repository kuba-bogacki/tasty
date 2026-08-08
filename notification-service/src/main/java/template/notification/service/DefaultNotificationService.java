package template.notification.service;

import common.events.deliver.CourierAssignedEvent;
import common.events.payment.PaymentCompletedEvent;
import common.events.payment.RefundCompletedEvent;
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
                .customerId(event.customerId())
                .type(NotificationType.PUSH)
                .content("Payment successfully finalized")
                .status(NotificationStatus.SENT)
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Payment notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void sendCourierAssigned(CourierAssignedEvent event) {
        final Notification notification = Notification.builder()
                .customerId(event.courierId())
                .type(NotificationType.PUSH)
                .content("Courier successfully assigned")
                .status(NotificationStatus.SENT)
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Assigned notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void sendRefundCompleted(RefundCompletedEvent event) {
        final Notification notification = Notification.builder()
                .customerId(event.orderId())
                .type(NotificationType.PUSH)
                .content("Refund successfully finalized")
                .status(NotificationStatus.SENT)
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Refund notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }
}
