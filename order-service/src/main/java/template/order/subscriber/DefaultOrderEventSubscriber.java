package template.order.subscriber;

import common.events.payment.RefundCompletedEvent;
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
    public void subscribeRefundCompleted(RefundCompletedEvent event) {
        log.info("Event 'refund payment' with id: {} successfully subscribed.", event.eventId());
        orderService.processCancel(event);
    }
}
