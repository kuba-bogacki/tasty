package template.payment.subscriber;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
import common.events.order.OrderWithdrawEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.payment.service.PaymentService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPaymentEventSubscriber implements PaymentEventSubscriber {

    private final PaymentService paymentService;

    @Override
    public void subscribeOrderCreated(OrderCreatedEvent event) {
        log.info("Event 'order created' with id: {} successfully subscribed.", event.eventId());
        paymentService.handleOrderCreated(event);
    }

    @Override
    public void subscribeOrderRejected(OrderRejectedEvent event) {
        log.info("Event 'order rejected' with id: {} successfully subscribed.", event.eventId());
        paymentService.handleOrderRejected(event);
    }

    @Override
    public void subscribeOrderWithdraw(OrderWithdrawEvent event) {
        log.info("Event 'order withdraw' with id: {} successfully subscribed.", event.eventId());
        paymentService.handleOrderWithdraw(event);
    }
}
