package template.order.publisher;

import template.order.domain.Order;

public interface OrderEventPublisher {
    void publishOrderCreated(Order order);
}
