package template.notification.subscriber;

import common.events.delivery.DeliveryAssignedEvent;
import common.events.order.*;
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
    public void subscribeOrderPrepared(OrderPreparedEvent event) {
        log.info("Event 'order prepared' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderPrepared(event);
    }

    @Override
    public void subscribeOrderSent(OrderSentEvent event) {
        log.info("Event 'order sent' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderSent(event);
    }

    @Override
    public void subscribeOrderDelivered(OrderDeliveredEvent event) {
        log.info("Event 'order delivered' with id: {} successfully subscribed.", event.eventId());
        notificationService.handleOrderDelivered(event);
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
