package template.order.subscriber;

import common.events.payment.PaymentRefundedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationRejectedEvent;
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
        orderService.acceptOrder(event);
    }

    @Override
    public void subscribePreparationRejected(PreparationRejectedEvent event) {
        log.info("Event 'preparation rejected' with id: {} successfully subscribed.", event.eventId());
        orderService.rejectOrder(event);
    }

    @Override
    public void subscribeRefundCompleted(PaymentRefundedEvent event) {
        log.info("Event 'refund payment' with id: {} successfully subscribed.", event.eventId());
        orderService.processCancel(event);
    }
}
