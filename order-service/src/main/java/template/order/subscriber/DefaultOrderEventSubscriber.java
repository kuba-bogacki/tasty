package template.order.subscriber;

import common.events.delivery.DeliverySentEvent;
import common.events.payment.PaymentFailedEvent;
import common.events.preparation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.order.service.OrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultOrderEventSubscriber implements OrderEventSubscriber {

    private final OrderService orderService;

    @Override
    public void subscribePreparationAccepted(PreparationAcceptedEvent event) {
        log.info("Event 'preparation accepted' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationAccepted(event);
    }

    @Override
    public void subscribePreparationRejected(PreparationRejectedEvent event) {
        log.info("Event 'preparation rejected' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationRejected(event);
    }

    @Override
    public void subscribePreparationWithdrew(PreparationWithdrewEvent event) {
        log.info("Event 'preparation withdrew' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationWithdrew(event);
    }

    @Override
    public void subscribePreparationStarted(PreparationStartedEvent event) {
        log.info("Event 'preparation started' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationStarted(event);
    }

    @Override
    public void subscribePreparationCompleted(PreparationCompletedEvent event) {
        log.info("Event 'preparation completed' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationCompleted(event);
    }

    @Override
    public void subscribePaymentFailed(PaymentFailedEvent event) {
        log.info("Event 'payment failed' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePaymentFailed(event);
    }

    @Override
    public void subscribeDeliverySent(DeliverySentEvent event) {
        log.info("Event 'delivery sent' with id: {} successfully subscribed.", event.eventId());
        orderService.handleDeliverySent(event);
    }
}
