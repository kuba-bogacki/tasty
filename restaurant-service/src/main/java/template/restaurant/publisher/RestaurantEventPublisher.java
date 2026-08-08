package template.restaurant.publisher;

import template.restaurant.domain.OrderPreparation;

public interface RestaurantEventPublisher {

    void publishOrderAccepted(OrderPreparation orderPreparation);
    void publishOrderRejected(OrderPreparation orderPreparation);
}
