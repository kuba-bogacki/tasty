package template.courier.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.courier.domain.Courier;
import template.courier.domain.dto.CourierDto;
import template.courier.domain.type.CourierStatus;
import template.courier.exception.CourierStatusException;
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
    public void releaseCourier(UUID courierId) {
        final Courier courier = findCourier(courierId);
        final Courier releaseCourier = courier.toBuilder()
                .status(CourierStatus.AVAILABLE)
                .orderId(null)
                .assignedAt(null)
                .build();
        final Courier updatedCourier = courierRepository.save(releaseCourier);
        log.info("Released courier with id {} successfully saved.", updatedCourier.getId());
    }

    @Override
    public void createNewCourier(CourierDto.Create createDto) {
        final Courier courier = Courier.builder()
                .name(createDto.name())
                .status(CourierStatus.AVAILABLE)
                .build();
        final Courier savedCourier = courierRepository.save(courier);
        log.info("Courier with id {} successfully saved.", savedCourier.getId());
    }

    @Override
    public Optional<CourierDto.Find> findAvailableCourier() {
        final Optional<Courier> courier = courierRepository.getFirstByStatusEquals(CourierStatus.AVAILABLE);
        if (courier.isEmpty()) {
            return Optional.empty();
        }
        final CourierDto.Find availableCourier = CourierDto.Find.builder()
                .id(courier.get().getId())
                .name(courier.get().getName())
                .build();
        return Optional.of(availableCourier);
    }

    @Override
    public void assignCourierToDelivery(CourierDto.Assign assignDto) {
        final Courier courier = findCourier(assignDto.id());
        final Courier assignCourier = courier.toBuilder()
                .status(CourierStatus.BUSY)
                .assignedAt(Instant.now())
                .build();
        final Courier updatedCourier = courierRepository.save(assignCourier);
        log.info("Updated 'Busy' courier with id {} successfully saved.", updatedCourier.getId());
    }

    private Courier findCourier(UUID courierId) {
        final Optional<Courier> courier = courierRepository.findById(courierId);
        if (courier.isEmpty()) {
            throw new CourierStatusException(String.format("Couldn't find courier with provided id: %s.", courierId));
        }
        return courier.get();
    }
}
