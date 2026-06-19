package template.restaurant.service;

import common.events.payment.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.restaurant.domain.OrderPreparation;
import template.restaurant.domain.type.PreparationStatus;
import template.restaurant.publisher.RestaurantEventPublisher;
import template.restaurant.repository.OrderPreparationRepository;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultRestaurantService implements RestaurantService {

    private final OrderPreparationRepository orderPreparationRepository;
    private final RestaurantEventPublisher restaurantEventPublisher;

    @Override
    public void acceptOrder(PaymentCompletedEvent event) {
        final OrderPreparation orderPreparation = OrderPreparation.builder()
                .orderId(event.orderId())
                .restaurantId(event.restaurantId())
                .status(PreparationStatus.ACCEPTED)
                .acceptedAt(Instant.now())
                .build();

        final OrderPreparation savedOrderPreparation = orderPreparationRepository.save(orderPreparation);
        log.info("New order preparation with id {} successfully saved.", savedOrderPreparation.getId());
        restaurantEventPublisher.publishOrderAccepted(savedOrderPreparation);
    }
}
