package template.payment.publisher;

import common.events.payment.PaymentCompletedEvent;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.payment.domain.Payment;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPaymentEventPublisher implements PaymentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishPaymentCompleted(Payment payment) {
        final PaymentCompletedEvent event = PaymentCompletedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .paymentId(payment.getId())
                .restaurantId(payment.getRestaurantId())
                .orderId(payment.getOrderId())
                .customerId(payment.getCustomerId())
                .amount(payment.getAmount())
                .build();

        kafkaTemplate.send(Topics.PAYMENT_COMPLETED, payment.getId().toString(), event);
        log.info("Event 'payment complete' with id: {} successfully published.", event.eventId());
    }
}
