package template.restaurant.service;

import common.events.payment.PaymentCompletedEvent;
import template.restaurant.domain.dto.RestaurantDto;

public interface RestaurantService {

    // Controller handlers
    void createRestaurant(RestaurantDto.Create createDto);
    void updateRestaurantStatus(RestaurantDto.Update updateDto);
    void withdrawPreparation(RestaurantDto.Withdraw withdrawDto);
    void startPreparation(RestaurantDto.Prepare prepareDto);

    // Events handlers
    void handlePaymentCompleted(PaymentCompletedEvent event);
}
