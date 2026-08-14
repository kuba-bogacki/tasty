package template.courier.service;

import common.events.order.OrderPreparingEvent;

public interface DeliveryService {

    // Controller handlers


    // Event handlers
    void handleOrderPreparing(OrderPreparingEvent event);
}
