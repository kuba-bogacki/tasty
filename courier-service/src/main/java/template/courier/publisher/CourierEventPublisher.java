package template.courier.publisher;

import template.courier.domain.Delivery;

public interface CourierEventPublisher {

    void publishCourierAssigned(Delivery delivery);
}
