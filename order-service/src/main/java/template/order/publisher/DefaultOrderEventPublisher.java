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
    public void publishOrderWithdrew(OrderDto.Withdraw rejectedOrder) {
        final OrderWithdrewEvent event = OrderWithdrewEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(rejectedOrder.id())
                .customerId(rejectedOrder.customerId())
                .restaurantId(rejectedOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_WITHDREW, rejectedOrder.id().toString(), event);
        log.info("Event 'order withdrew' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderStarted(OrderDto.Start startOrder) {
        final OrderStartedEvent event = OrderStartedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(startOrder.id())
                .restaurantId(startOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_STARTED, startOrder.id().toString(), event);
        log.info("Event 'order started' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderPrepared(OrderDto.Prepare prepareOrder) {
        final OrderPreparedEvent event = OrderPreparedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(prepareOrder.id())
                .customerId(prepareOrder.customerId())
                .restaurantId(prepareOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_PREPARED, prepareOrder.id().toString(), event);
        log.info("Event 'order prepared' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderSent(OrderDto.Send sendOrder) {
        final OrderSentEvent event = OrderSentEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(sendOrder.id())
                .customerId(sendOrder.customerId())
                .build();

        kafkaTemplate.send(Topics.ORDER_SENT, sendOrder.id().toString(), event);
        log.info("Event 'order sent' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderDelivered(OrderDto.Deliver deliverOrder) {
        final OrderDeliveredEvent event = OrderDeliveredEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(deliverOrder.id())
                .courierId(deliverOrder.courierId())
                .restaurantId(deliverOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_DELIVERED, deliverOrder.id().toString(), event);
        log.info("Event 'order delivered' with id: {} successfully published.", event.eventId());
    }
}
