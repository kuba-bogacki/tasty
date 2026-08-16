package template.notification.service;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.delivery.DeliverySentEvent;
import common.events.order.*;
import common.events.payment.PaymentRefundedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.notification.domain.Notification;
import template.notification.domain.type.NotificationExternalType;
import template.notification.domain.type.NotificationStatus;
import template.notification.domain.type.NotificationType;
import template.notification.repository.NotificationRepository;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultNotificationService implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void handleOrderAccepted(OrderAcceptedEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.customerId())
                .externalType(NotificationExternalType.CUSTOMER)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Order successfully accepted")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Order accepted notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handleOrderCancelled(OrderCancelledEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.customerId())
                .externalType(NotificationExternalType.CUSTOMER)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Order cancelled due to payment failure")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Order cancelled notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handleOrderPrepared(OrderPreparedEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.customerId())
                .externalType(NotificationExternalType.CUSTOMER)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Order successfully prepared")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Order prepared notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handlePaymentRefunded(PaymentRefundedEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.orderId())
                .externalType(NotificationExternalType.ORDER)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Refund successfully finalized")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Payment refunded notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handleDeliveryAssigned(DeliveryAssignedEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.deliveryId())
                .externalType(NotificationExternalType.DELIVERY)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Delivery successfully assigned to courier")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Delivery assigned notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handleOrderSent(OrderSentEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.customerId())
                .externalType(NotificationExternalType.CUSTOMER)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Order successfully sent to customer")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Order sent notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }

    @Override
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        final Notification notification = Notification.builder()
                .externalId(event.restaurantId())
                .externalType(NotificationExternalType.RESTAURANT)
                .type(NotificationType.PUSH)
                .status(NotificationStatus.SENT)
                .content("Order successfully delivered to customer")
                .createdAt(Instant.now())
                .build();

        final Notification savedNotification = notificationRepository.save(notification);
        log.info("Order delivered notification '{}' with id: {} successfully saved.", savedNotification.getContent(), savedNotification.getId());
    }
}
