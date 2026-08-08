package template.order.publisher;

import common.events.order.OrderCreatedEvent;
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
        log.info("Event 'create order' with id: {} successfully published.", event.eventId());
    }
}
