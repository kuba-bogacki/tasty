package template.order.service;

import common.events.delivery.DeliverySentEvent;
import common.events.payment.PaymentFailedEvent;
import common.events.preparation.*;
import template.order.domain.dto.OrderDto;

public interface OrderService {

    // Controller handlers
    void createOrder(OrderDto.Create createDto);
    void deliverOrder(OrderDto.Deliver createDto);

    // Event handlers
    void handlePreparationAccepted(PreparationAcceptedEvent event);
    void handlePreparationRejected(PreparationRejectedEvent event);
    void handlePreparationWithdrew(PreparationWithdrewEvent event);
    void handlePreparationStarted(PreparationStartedEvent event);
    void handlePreparationCompleted(PreparationCompletedEvent event);
    void handlePaymentFailed(PaymentFailedEvent event);
    void handleDeliverySent(DeliverySentEvent event);
}
