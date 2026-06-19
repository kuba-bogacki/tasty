package template.courier.service;

import template.courier.domain.dto.CourierDto;

import java.util.UUID;

public interface CourierService {

    void createNewCourier(CourierDto courierDto);
    UUID getAvailableCourier();
    void assignCourierToDelivery(UUID courierId);
}
