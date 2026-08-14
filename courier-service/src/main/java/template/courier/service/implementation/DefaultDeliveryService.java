package template.courier.service.implementation;

import common.events.order.OrderPreparingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import template.courier.domain.Delivery;
import template.courier.domain.dto.CourierDto;
import template.courier.domain.dto.DeliveryDto;
import template.courier.domain.type.DeliveryStatus;
import template.courier.exception.CourierStatusException;
import template.courier.publisher.CourierEventPublisher;
import template.courier.repository.DeliveryRepository;
import template.courier.service.CourierService;
import template.courier.service.DeliveryService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultDeliveryService implements DeliveryService {

    private final CourierService courierService;
    private final DeliveryRepository deliveryRepository;
    private final CourierEventPublisher courierEventPublisher;

    @Override
    @Transactional
    public void handleOrderPreparing(OrderPreparingEvent event) {
        final Optional<CourierDto.Find> courier = courierService.findAvailableCourier();
        if (courier.isEmpty()) {
            throw new CourierStatusException("No one courier is available.");
        }

        final CourierDto.Assign assignCourier = CourierDto.Assign.builder()
                .id(courier.get().id())
                .orderId(event.orderId())
                .build();
        courierService.assignCourierToDelivery(assignCourier);

        final Delivery delivery = Delivery.builder()
                .orderId(event.orderId())
                .courierId(courier.get().id())
                .status(DeliveryStatus.ASSIGNED)
                .build();
        final Delivery savedDelivery = deliveryRepository.save(delivery);
        log.info("New delivery with id {} successfully saved.", savedDelivery.getId());

        final DeliveryDto.Assigned assignedDelivery = DeliveryDto.Assigned.builder()
                .id(savedDelivery.getId())
                .orderId(savedDelivery.getOrderId())
                .courierId(savedDelivery.getCourierId())
                .build();
        courierEventPublisher.publishDeliveryAssigned(assignedDelivery);
    }
}
