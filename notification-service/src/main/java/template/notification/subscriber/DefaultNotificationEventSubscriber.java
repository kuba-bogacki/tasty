package template.notification.subscriber;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderCancelledEvent;
import common.events.order.OrderReadyEvent;
import common.events.payment.PaymentRefundedEvent;
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
    public void subscribeOrderAccepted(OrderAcceptedEvent event) {
        log.info("Event 'order accepted' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderAccepted(event);
    }

    @Override
    public void subscribeOrderCancelled(OrderCancelledEvent event) {
        log.info("Event 'order cancelled' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderCancelled(event);
    }

    @Override
    public void subscribeOrderReady(OrderReadyEvent event) {
        log.info("Event 'order ready for pick up' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderReady(event);
    }

    @Override
    public void subscribeDeliveryAssigned(DeliveryAssignedEvent event) {
        log.info("Event 'delivery assigned' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleDeliveryAssigned(event);
    }

    @Override
    public void subscribePaymentRefunded(PaymentRefundedEvent event) {
        log.info("Event 'payment refunded' with id: {} successfully subscribed.", event.eventId());
        notificationService.handlePaymentRefunded(event);
    }
}
