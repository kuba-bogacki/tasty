package template.payment.client;

import template.payment.domain.dto.PaymentDto;

public interface PaymentProvider {

    boolean processPayment(PaymentDto.Process paymentProcess);
}
