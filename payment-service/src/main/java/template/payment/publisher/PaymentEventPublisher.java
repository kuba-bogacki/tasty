package template.payment.publisher;

import template.payment.domain.dto.PaymentDto;

public interface PaymentEventPublisher {

    void publishPaymentCompleted(PaymentDto.Completed completedPayment);
    void publishPaymentFailed(PaymentDto.Failed failedPayment);
    void publishPaymentRefunded(PaymentDto.Refund refundPayment);
}
