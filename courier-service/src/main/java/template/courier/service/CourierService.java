package template.courier.service;

import template.courier.domain.dto.CourierDto;

import java.util.Optional;
import java.util.UUID;

public interface CourierService {

    void createNewCourier(CourierDto.Create createDto);
    void assignCourierToDelivery(CourierDto.Assign assignDto);
    Optional<CourierDto.Find> findAvailableCourier();
}
