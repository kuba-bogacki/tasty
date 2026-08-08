package template.order.publisher;

import common.events.order.OrderAcceptedEvent;
import common.events.order.OrderCreatedEvent;
import common.events.order.OrderRejectedEvent;
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
                .orderId(publishOrder.orderId())
                .customerId(publishOrder.customerId())
                .restaurantId(publishOrder.restaurantId())
                .paymentMethod(publishOrder.paymentMethod())
                .totalAmount(publishOrder.totalAmount())
                .build();

        kafkaTemplate.send(Topics.ORDER_CREATED, publishOrder.orderId().toString(), event);
        log.info("Event 'order created' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderAccepted(OrderDto.Accept acceptOrder) {
        final OrderAcceptedEvent event = OrderAcceptedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(acceptOrder.orderId())
                .restaurantId(acceptOrder.restaurantId())
                .build();

        kafkaTemplate.send(Topics.ORDER_ACCEPTED, acceptOrder.orderId().toString(), event);
        log.info("Event 'order accepted' with id: {} successfully published.", event.eventId());
    }

    @Override
    public void publishOrderRejected(OrderDto.Reject rejectOrder) {
        final OrderRejectedEvent event = OrderRejectedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .orderId(rejectOrder.orderId())
                .restaurantId(rejectOrder.restaurantId())
                .reason(rejectOrder.reason())
                .build();

        kafkaTemplate.send(Topics.ORDER_REJECTED, rejectOrder.orderId().toString(), event);
        log.info("Event 'order rejected' with id: {} successfully published.", event.eventId());
    }
}
