package template.courier.subscriber;

import common.events.order.OrderAcceptedEvent;
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
    public void subscribeOrderAccepted(OrderAcceptedEvent event) {
        log.info("Event 'order accepted' with id: {} successfully subscribed.", event.eventId());
        deliveryService.processDeliver(event);
    }
}
