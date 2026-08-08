package template.payment.publisher;

import common.events.payment.PaymentCompletedEvent;
import common.events.payment.PaymentFailedEvent;
import common.events.payment.PaymentRefundedEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.payment.domain.dto.PaymentDto;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPaymentEventPublisher implements PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishPaymentCompleted(PaymentDto.Completed completedPayment) {
        final PaymentCompletedEvent event = PaymentCompletedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .paymentId(completedPayment.paymentId())
                .restaurantId(completedPayment.restaurantId())
                .orderId(completedPayment.orderId())
                .customerId(completedPayment.customerId())
                .amount(completedPayment.amount())
                .build();

        kafkaTemplate.send(Topics.PAYMENT_COMPLETED, completedPayment.paymentId().toString(), event);
        log.info("Event 'payment complete' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishPaymentFailed(PaymentDto.Failed failedPayment) {
        final PaymentFailedEvent event = PaymentFailedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .paymentId(failedPayment.paymentId())
                .orderId(failedPayment.orderId())
                .customerId(failedPayment.customerId())
                .reason(failedPayment.reason())
                .build();

        kafkaTemplate.send(Topics.PAYMENT_FAILED, failedPayment.paymentId().toString(), event);
        log.info("Event 'payment failed' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishPaymentRefunded(PaymentDto.Refund refundPayment) {
        final PaymentRefundedEvent event = PaymentRefundedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .paymentId(refundPayment.paymentId())
                .orderId(refundPayment.orderId())
                .customerId(refundPayment.customerId())
                .build();

        kafkaTemplate.send(Topics.PAYMENT_REFUNDED, refundPayment.paymentId().toString(), event);
        log.info("Event 'payment refunded' with id: {} successfully published.", event.eventId());
    }
}
