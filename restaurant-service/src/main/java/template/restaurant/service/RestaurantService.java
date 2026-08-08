package template.restaurant.service;

import common.events.payment.PaymentCompletedEvent;
import template.restaurant.domain.dto.RestaurantDto;

public interface RestaurantService {

    void createRestaurant(RestaurantDto.Create createDto);
    void updateRestaurantStatus(RestaurantDto.Update updateDto);
    void acceptOrder(PaymentCompletedEvent event);
}
