package template.payment.subscriber;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
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
        paymentService.processPayment(event);
    }

    @Override
    public void subscribeOrderRejected(OrderRejectedEvent event) {
        log.info("Event 'order rejected' with id: {} successfully subscribed.", event.eventId());
        paymentService.processRefund(event);
    }
}
