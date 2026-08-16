package template.courier.service;

import common.events.order.OrderDeliveredEvent;
import common.events.order.OrderStartedEvent;
import template.courier.domain.dto.DeliveryDto;

public interface DeliveryService {

    // Controller handlers
    void sendDelivery(DeliveryDto.Send sendDto);

    // Event handlers
    void handleOrderStarted(OrderStartedEvent event);
    void handleOrderDelivered(OrderDeliveredEvent event);
}
