package template.courier.subscriber;

import common.events.order.OrderPreparingEvent;
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
    public void subscribeOrderPreparing(OrderPreparingEvent event) {
        log.info("Event 'order preparing' with id: {} successfully subscribed.", event.eventId());
        deliveryService.handleOrderPreparing(event);
    }
}
