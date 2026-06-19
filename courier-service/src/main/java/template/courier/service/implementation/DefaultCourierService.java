package template.courier.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.courier.domain.Courier;
import template.courier.domain.dto.CourierDto;
import template.courier.domain.type.CourierStatus;
import template.courier.exception.CourierException;
import template.courier.repository.CourierRepository;
import template.courier.service.CourierService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCourierService implements CourierService {

    private final CourierRepository courierRepository;

    @Override
    public void createNewCourier(CourierDto courierDto) {
        final Courier courier = Courier.builder()
                .name(courierDto.name())
                .status(CourierStatus.AVAILABLE)
                .build();
        final Courier savedCourier = courierRepository.save(courier);
        log.info("Courier with id {} successfully saved.", savedCourier.getId());
    }

    @Override
    public UUID getAvailableCourier() {
        final Optional<Courier> courier = Optional.ofNullable(courierRepository.getFirstByStatusEquals(CourierStatus.AVAILABLE));
        return courier
                .orElseThrow(() -> new CourierException("No one courier is available."))
                .getId();
    }

    @Override
    public void assignCourierToDelivery(UUID courierId) {
        final Optional<Courier> courier = courierRepository.findById(courierId);
        if (courier.isEmpty()) {
            throw new CourierException(String.format("Couldn't find courier with provided id: %s.", courierId));
        }
        final Courier updatedCourier = courier.get().toBuilder()
                .status(CourierStatus.BUSY)
                .assignedAt(Instant.now())
                .build();
        courierRepository.save(updatedCourier);
        log.info("Courier with id {} successfully updated.", courier.get().getId());
    }
}
