package template.restaurant.service;

import common.events.payment.PaymentCompletedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.restaurant.domain.OrderPreparation;
import template.restaurant.domain.Restaurant;
import template.restaurant.domain.dto.OrderPreparationDto;
import template.restaurant.domain.dto.RestaurantDto;
import template.restaurant.domain.type.PreparationStatus;
import template.restaurant.domain.type.RestaurantStatus;
import template.restaurant.publisher.RestaurantEventPublisher;
import template.restaurant.repository.OrderPreparationRepository;
import template.restaurant.repository.RestaurantRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultRestaurantService implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final OrderPreparationRepository orderPreparationRepository;
    private final RestaurantEventPublisher restaurantEventPublisher;

    @Override
    public void createRestaurant(RestaurantDto.Create createDto) {
        final Restaurant restaurant = Restaurant.builder()
                .name(createDto.name())
                .description(createDto.description())
                .status(RestaurantStatus.fromString(createDto.status()))
                .phoneNumber(createDto.phoneNumber())
                .build();

        final Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        log.info("New saved restaurant with id {} successfully saved.", savedRestaurant.getId());
    }

    @Override
    public void updateRestaurantStatus(RestaurantDto.Update updateDto) {
        final Optional<Restaurant> restaurant = restaurantRepository.findById(UUID.fromString(updateDto.id()));
        if (restaurant.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find restaurant with id equal: %s", updateDto.id()));
        }

        restaurant.get().setStatus(RestaurantStatus.fromString(updateDto.status()));

        final Restaurant savedRestaurant = restaurantRepository.save(restaurant.get());
        log.info("Restaurant with updated status and with id {} successfully saved.", savedRestaurant.getId());
    }

    @Override
    public void acceptOrder(PaymentCompletedEvent event) {
        final Optional<Restaurant> restaurant = restaurantRepository.findById(event.restaurantId());
        if (restaurant.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find restaurant with id equal: %s", event.restaurantId()));
        }

        final OrderPreparation orderPreparation = OrderPreparation.builder()
                .orderId(event.orderId())
                .restaurantId(event.restaurantId())
                .build();

        if (RestaurantStatus.CLOSED == restaurant.get().getStatus()) {
            final OrderPreparation preparationRejected = orderPreparation.toBuilder()
                    .status(PreparationStatus.REJECTED)
                    .build();

            final OrderPreparation savedRejectedPreparation = orderPreparationRepository.save(preparationRejected);
            log.warn("Restaurant closed, cannot finalize order. Refund was requested.");

            final OrderPreparationDto.Rejected rejectedPreparation = OrderPreparationDto.Rejected.builder()
                    .orderId(savedRejectedPreparation.getOrderId())
                    .restaurantId(savedRejectedPreparation.getRestaurantId())
                    .reason("Restaurant has been closed.")
                    .build();
            restaurantEventPublisher.publishPreparationRejected(rejectedPreparation);
        } else {
            final OrderPreparation preparationAccepted = orderPreparation.toBuilder()
                    .status(PreparationStatus.ACCEPTED)
                    .acceptedAt(Instant.now())
                    .build();

            final OrderPreparation savedAcceptedPreparation = orderPreparationRepository.save(preparationAccepted);
            log.info("New order preparation with id {} successfully saved.", savedAcceptedPreparation.getId());

            final OrderPreparationDto.Accepted acceptedPreparation = OrderPreparationDto.Accepted.builder()
                    .orderId(savedAcceptedPreparation.getOrderId())
                    .restaurantId(savedAcceptedPreparation.getRestaurantId())
                    .build();
            restaurantEventPublisher.publishPreparationAccepted(acceptedPreparation);
        }
    }
}
