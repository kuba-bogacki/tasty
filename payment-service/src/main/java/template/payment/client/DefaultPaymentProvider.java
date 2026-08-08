package template.payment.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import template.payment.domain.dto.PaymentDto;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DefaultPaymentProvider implements PaymentProvider {

    private static final int RANDOM_MIN_VALUE = 0;
    private static final int RANDOM_MAX_VALUE = 5001;

    @Override
    public boolean processPayment(PaymentDto.Process paymentProcess) {
        try {
            final long delayMillis = ThreadLocalRandom.current().nextLong(RANDOM_MIN_VALUE, RANDOM_MAX_VALUE);
            TimeUnit.MILLISECONDS.sleep(delayMillis);
            final boolean isSuccess = ThreadLocalRandom.current().nextDouble() < 0.90;

            final String message = isSuccess
                    ? String.format("Payment accepted.\n%s", getFormattedPaymentDetails(paymentProcess))
                    : "Payment refused: No funds in the account.";

            log.info(message);
            return isSuccess;
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            log.error("Payment has been interrupted. Cause: {}.", exception.getMessage());
            return false;
        }
    }

    @Override
    public void processPaymentRefund(PaymentDto.Process paymentProcess) {
        try {
            final long delayMillis = ThreadLocalRandom.current().nextLong(RANDOM_MIN_VALUE, RANDOM_MAX_VALUE);
            TimeUnit.MILLISECONDS.sleep(delayMillis);

            log.info("Refund payment success.\n{}", getFormattedPaymentDetails(paymentProcess));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            log.error("Refund payment error. Cause: {}.", exception.getMessage());
        }
    }

    private String getFormattedPaymentDetails(PaymentDto.Process paymentProcess) {
        return String.format("""
                Payment details:
                
                Customer id - %s
                Restaurant id - %s
                Payment method - %s
                Total amount - %s
                """,
                paymentProcess.customerId(), paymentProcess.restaurantId(),
                paymentProcess.method().getDescription(), paymentProcess.totalAmount());
    }
}
