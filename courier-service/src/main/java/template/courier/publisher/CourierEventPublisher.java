package template.courier.publisher;

import template.courier.domain.dto.DeliveryDto;

public interface CourierEventPublisher {

    void publishDeliveryAssigned(DeliveryDto.Assigned assignedDelivery);
}
