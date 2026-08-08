package template.order.publisher;

import template.order.domain.dto.OrderDto;

public interface OrderEventPublisher {
    void publishOrderCreated(OrderDto.Publish publishOrder);
}
