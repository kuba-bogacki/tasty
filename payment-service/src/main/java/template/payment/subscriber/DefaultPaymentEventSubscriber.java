package template.payment.subscriber;

import common.events.order.OrderCreatedEvent;
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
        log.info("Event 'create order' with id: {} successfully subscribed.", event.eventId());
        paymentService.processPayment(event);
    }
}
