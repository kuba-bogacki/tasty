package template.restaurant.publisher;

import common.events.order.OrderAcceptedEvent;
import template.restaurant.domain.OrderPreparation;

public interface RestaurantEventPublisher {

    void publishOrderAccepted(OrderPreparation orderPreparation);
}
