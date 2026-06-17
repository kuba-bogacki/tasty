package template.payment.service;

import common.events.order.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import template.payment.domain.Payment;
import template.payment.domain.type.PaymentMethod;
import template.payment.domain.type.PaymentStatus;
import template.payment.publisher.PaymentEventPublisher;
import template.payment.repository.PaymentRepository;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    @Override
    @Transactional
    public void processPayment(OrderCreatedEvent event) {
        final Payment payment = Payment.builder()
                .orderId(event.orderId())
                .amount(event.totalAmount())
                .status(PaymentStatus.COMPLETED)
                .method(PaymentMethod.CASH)
                .createdAt(Instant.now())
                .build();

        final Payment savedPayment = paymentRepository.save(payment);
        log.info("New payment with id {} successfully saved.", savedPayment.getId());
        paymentEventPublisher.publishPaymentCompleted(savedPayment);
    }
}
