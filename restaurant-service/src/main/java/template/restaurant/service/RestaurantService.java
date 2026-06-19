package template.restaurant.service;

import common.events.payment.PaymentCompletedEvent;

public interface RestaurantService {

    void acceptOrder(PaymentCompletedEvent event);
}
