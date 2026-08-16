package template.courier.publisher;

import template.courier.domain.dto.DeliveryDto;

public interface CourierEventPublisher {

    void publishDeliveryAssigned(DeliveryDto.Assign assignDelivery);
    void publishDeliverySent(DeliveryDto.Send sendDelivery);
}
