package template.courier.service;

import common.events.order.OrderAcceptedEvent;

public interface DeliveryService {

    void processDeliver(OrderAcceptedEvent event);
}
