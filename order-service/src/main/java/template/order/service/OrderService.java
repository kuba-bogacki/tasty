package template.order.service;

import common.events.payment.PaymentFailedEvent;
import common.events.preparation.PreparationAcceptedEvent;
import common.events.preparation.PreparationInProgressEvent;
import common.events.preparation.PreparationRejectedEvent;
import common.events.preparation.PreparationWithdrawEvent;
import template.order.domain.dto.OrderDto;

public interface OrderService {

    // Controller handlers
    void createOrder(OrderDto.Create createDto);

    // Event handlers
    void handlePreparationAccepted(PreparationAcceptedEvent event);
    void handlePreparationRejected(PreparationRejectedEvent event);
    void handlePreparationWithdraw(PreparationWithdrawEvent event);
    void handlePreparationInProgress(PreparationInProgressEvent event);
    void handlePaymentFailed(PaymentFailedEvent event);
}
