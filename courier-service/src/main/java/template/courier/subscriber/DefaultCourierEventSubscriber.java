package template.courier.subscriber;

import common.events.order.OrderDeliveredEvent;
import common.events.order.OrderStartedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.courier.service.DeliveryService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultCourierEventSubscriber implements CourierEventSubscriber {

    private final DeliveryService deliveryService;

    @Override
    public void subscribeOrderStarted(OrderStartedEvent event) {
        log.info("Event 'order started' with id: {} successfully subscribed.", event.eventId());
        deliveryService.handleOrderStarted(event);
    }

    @Override
    public void subscribeOrderDelivered(OrderDeliveredEvent event) {
        log.info("Event 'order delivered' with id: {} successfully subscribed.", event.eventId());
        deliveryService.handleOrderDelivered(event);
    }
}
