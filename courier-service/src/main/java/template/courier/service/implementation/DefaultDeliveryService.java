package template.courier.service.implementation;

import common.events.order.OrderAcceptedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import template.courier.domain.Delivery;
import template.courier.domain.type.DeliveryStatus;
import template.courier.publisher.CourierEventPublisher;
import template.courier.repository.DeliveryRepository;
import template.courier.service.CourierService;
import template.courier.service.DeliveryService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultDeliveryService implements DeliveryService {

    private final CourierService courierService;
    private final DeliveryRepository deliveryRepository;
    private final CourierEventPublisher courierEventPublisher;

    @Override
    @Transactional
    public void processDeliver(OrderAcceptedEvent event) {
        final UUID courierId = courierService.getAvailableCourier();
        final Delivery delivery = Delivery.builder()
                .orderId(event.orderId())
                .courierId(courierId)
                .status(DeliveryStatus.ASSIGNED)
                .build();

        final Delivery savedDelivery = deliveryRepository.save(delivery);
        courierService.assignCourierToDelivery(courierId);
        log.info("Delivery with id {} successfully saved.", savedDelivery.getId());

        courierEventPublisher.publishCourierAssigned(savedDelivery);
    }
}
