package template.notification.subscriber;

import common.events.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.notification.service.NotificationService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultNotificationEventSubscriber implements NotificationEventSubscriber {

    private final NotificationService notificationService;

    @Override
    public void subscribePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Event 'payment completed' with id: {} successfully subscribed.", event.eventId());
        notificationService.sendPaymentNotify(event);
    }
}
