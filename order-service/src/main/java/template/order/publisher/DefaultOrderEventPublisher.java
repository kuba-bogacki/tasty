package template.order.publisher;

import common.events.order.*;
import common.events.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import template.order.domain.dto.OrderDto;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultOrderEventPublisher implements OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishOrderCreated(OrderDto.Publish publishOrder) {
        final OrderCreatedEvent event = OrderCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(publishOrder.id())
                .customerId(publishOrder.customerId())
                .restaurantId(publishOrder.restaurantId())
                .paymentMethod(publishOrder.paymentMethod())
                .totalAmount(publishOrder.totalAmount())
                .build();

        kafkaTemplate.send(Topics.ORDER_CREATED, publishOrder.id().toString(), event);
        log.info("Event 'order created' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderAccepted(OrderDto.Accept acceptOrder) {
        final OrderAcceptedEvent event = OrderAcceptedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(acceptOrder.id())
                .customerId(acceptOrder.customerId())
                .restaurantId(acceptOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_ACCEPTED, acceptOrder.id().toString(), event);
        log.info("Event 'order accepted' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderRejected(OrderDto.Reject rejectOrder) {
        final OrderRejectedEvent event = OrderRejectedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(rejectOrder.id())
                .restaurantId(rejectOrder.restaurantId())
                .reason(rejectOrder.reason())
                .build();

        kafkaTemplate.send(Topics.ORDER_REJECTED, rejectOrder.id().toString(), event);
        log.info("Event 'order rejected' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderCancelled(OrderDto.Cancel cancelOrder) {
        final OrderCancelledEvent event = OrderCancelledEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(cancelOrder.id())
                .customerId(cancelOrder.customerId())
                .restaurantId(cancelOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_CANCELLED, cancelOrder.id().toString(), event);
        log.info("Event 'order cancelled' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderWithdraw(OrderDto.Withdraw rejectedOrder) {
        final OrderWithdrawEvent event = OrderWithdrawEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(rejectedOrder.id())
                .customerId(rejectedOrder.customerId())
                .restaurantId(rejectedOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_WITHDRAW, rejectedOrder.id().toString(), event);
        log.info("Event 'order withdraw' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderPreparing(OrderDto.Prepare prepareOrder) {
        final OrderPreparingEvent event = OrderPreparingEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(prepareOrder.id())
                .restaurantId(prepareOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_PREPARING, prepareOrder.id().toString(), event);
        log.info("Event 'order preparing' with id: {} successfully published.", event.eventId());
    }
}
