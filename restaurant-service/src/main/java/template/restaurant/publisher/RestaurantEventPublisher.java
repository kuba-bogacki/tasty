package template.restaurant.publisher;

import template.restaurant.domain.dto.OrderPreparationDto;

public interface RestaurantEventPublisher {

    void publishPreparationAccepted(OrderPreparationDto.Accepted acceptedPreparation);
    void publishPreparationRejected(OrderPreparationDto.Rejected rejectedPreparation);
    void publishPreparationWithdraw(OrderPreparationDto.Withdraw withdrawPreparation);
    void publishPreparationInProgress(OrderPreparationDto.Prepare startPreparation);
    void publishPreparationReady(OrderPreparationDto.Ready readyPreparation);
}
