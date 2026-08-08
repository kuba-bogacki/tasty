package template.payment.service;

import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import template.payment.client.PaymentProvider;
import template.payment.domain.Payment;
import template.payment.domain.dto.PaymentDto;
import template.payment.domain.type.PaymentMethod;
import template.payment.domain.type.PaymentStatus;
import template.payment.publisher.PaymentEventPublisher;
import template.payment.repository.PaymentRepository;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final PaymentProvider paymentProvider;
    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    @Override
    @Transactional
    public void processPayment(OrderCreatedEvent event) {
        final Payment payment = Payment.builder()
                .orderId(event.orderId())
                .customerId(event.customerId())
                .restaurantId(event.restaurantId())
                .amount(event.totalAmount())
                .status(PaymentStatus.PENDING)
                .method(PaymentMethod.fromString(event.paymentMethod()))
                .createdAt(Instant.now())
                .build();

        final Payment savedPayment = paymentRepository.save(payment);
        log.info("New payment with id {} successfully saved.", savedPayment.getId());

        final PaymentDto.Process paymentProcess = PaymentDto.Process.builder()
                .customerId(savedPayment.getCustomerId())
                .restaurantId(savedPayment.getRestaurantId())
                .method(savedPayment.getMethod())
                .totalAmount(savedPayment.getAmount())
                .build();

        final boolean paymentSucceeded = paymentProvider.processPayment(paymentProcess);
        final PaymentStatus paymentStatus = paymentSucceeded ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;

        savedPayment.setStatus(paymentStatus);
        paymentRepository.save(savedPayment);

        log.info("Payment with status {} and id {} successfully updated.", savedPayment.getId(), paymentStatus.getDescription());

        if (paymentSucceeded) {
            final PaymentDto.Completed completedPayment = PaymentDto.Completed.builder()
                    .paymentId(savedPayment.getId())
                    .restaurantId(savedPayment.getRestaurantId())
                    .orderId(savedPayment.getOrderId())
                    .customerId(savedPayment.getCustomerId())
                    .amount(savedPayment.getAmount())
                    .build();
            paymentEventPublisher.publishPaymentCompleted(completedPayment);
        } else {
            final PaymentDto.Failed failedPayment = PaymentDto.Failed.builder()
                    .paymentId(savedPayment.getId())
                    .orderId(savedPayment.getOrderId())
                    .customerId(savedPayment.getCustomerId())
                    .reason("No funds in the account.")
                    .build();
            paymentEventPublisher.publishPaymentFailed(failedPayment);
        }
    }

    @Override
    @Transactional
    public void processRefund(OrderRejectedEvent event) {
        final Optional<Payment> completedPayment = paymentRepository.findByOrderId(event.orderId());
        if (completedPayment.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find payment accessing order id equal: %s", event.orderId()));
        }

        final Payment refundPayment = Payment.builder()
                .orderId(event.orderId())
                .customerId(completedPayment.get().getCustomerId())
                .restaurantId(event.restaurantId())
                .amount(completedPayment.get().getAmount())
                .status(PaymentStatus.REFUNDED)
                .method(PaymentMethod.CARD)
                .createdAt(Instant.now())
                .build();

        final Payment savedRefundPayment = paymentRepository.save(refundPayment);
        log.info("New refund payment with id {} successfully saved.", savedRefundPayment.getId());
        paymentEventPublisher.publishRefundCompleted(savedRefundPayment);
    }
}
