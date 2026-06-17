package template.payment.publisher;

import template.payment.domain.Payment;

public interface PaymentEventPublisher {

    void publishPaymentCompleted(Payment payment);
}
