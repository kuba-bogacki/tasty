package template.order.subscriber;

import common.events.payment.PaymentFailedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationInProgressEvent;
import common.events.preparation.PreparationRejectedEvent;
import common.events.preparation.PreparationWithdrawEvent;
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
    public void subscribePreparationWithdraw(PreparationWithdrawEvent event) {
        log.info("Event 'preparation withdraw' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationWithdraw(event);
    }

    @Override
    public void subscribePreparationInProgress(PreparationInProgressEvent event) {
        log.info("Event 'preparation in progress' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePreparationInProgress(event);
    }

    @Override
    public void subscribePaymentFailed(PaymentFailedEvent event) {
        log.info("Event 'payment failed' with id: {} successfully subscribed.", event.eventId());
        orderService.handlePaymentFailed(event);
    }
}
