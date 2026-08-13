package template.restaurant.subscriber;

import common.events.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.restaurant.service.RestaurantService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultRestaurantEventSubscriber implements RestaurantEventSubscriber {

    private final RestaurantService restaurantService;

    @Override
    public void subscribePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Event 'payment completed' with id: {} successfully subscribed.", event.eventId());
        restaurantService.handlePaymentCompleted(event);
    }
}
