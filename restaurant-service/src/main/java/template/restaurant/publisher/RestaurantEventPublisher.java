package template.restaurant.publisher;

import template.restaurant.domain.dto.OrderPreparationDto;

public interface RestaurantEventPublisher {

    void publishPreparationAccepted(OrderPreparationDto.Accepted acceptedPreparation);
    void publishPreparationRejected(OrderPreparationDto.Rejected rejectedPreparation);
    void publishPreparationWithdrew(OrderPreparationDto.Withdraw withdrawPreparation);
    void publishPreparationStarted(OrderPreparationDto.Start startPreparation);
    void publishPreparationCompleted(OrderPreparationDto.Complete completePreparation);
}
