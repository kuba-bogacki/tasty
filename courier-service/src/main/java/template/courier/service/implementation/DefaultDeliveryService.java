package template.courier.service.implementation;

import common.events.order.OrderDeliveredEvent;
import common.events.order.OrderStartedEvent;
import jakarta.persistence.EntityNotFoundException;
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

import java.time.Instant;
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
    public void sendDelivery(DeliveryDto.Send sendDto) {
        final Optional<Delivery> delivery = deliveryRepository.findById(sendDto.id());
        if (delivery.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find delivery with id equal: %s", sendDto.id()));
        }

        delivery.get().setStatus(DeliveryStatus.SENT);
        final Delivery savedDelivery = deliveryRepository.save(delivery.get());
        log.info("Delivery with 'Sent' status and with id {} successfully updated.", savedDelivery.getId());

        final DeliveryDto.Send sendDelivery = DeliveryDto.Send.builder()
                .id(savedDelivery.getId())
                .orderId(savedDelivery.getOrderId())
                .build();
        courierEventPublisher.publishDeliverySent(sendDelivery);
    }

    @Override
    @Transactional
    public void handleOrderStarted(OrderStartedEvent event) {
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

        final DeliveryDto.Assign assignDelivery = DeliveryDto.Assign.builder()
                .id(savedDelivery.getId())
                .orderId(savedDelivery.getOrderId())
                .courierId(savedDelivery.getCourierId())
                .build();
        courierEventPublisher.publishDeliveryAssigned(assignDelivery);
    }

    @Override
    @Transactional
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        final Optional<Delivery> delivery = deliveryRepository.findByOrderId(event.orderId());
        if (delivery.isEmpty()) {
            throw new EntityNotFoundException(String.format("Couldn't find delivery with assigned order id equal: %s", event.orderId()));
        }

        delivery.get().setStatus(DeliveryStatus.DELIVERED);
        delivery.get().setDeliveredAt(Instant.now());
        final Delivery savedDelivery = deliveryRepository.save(delivery.get());
        log.info("Delivery with 'Delivered' status and with id {} successfully updated.", savedDelivery.getId());

        courierService.releaseCourier(event.courierId());
    }
}
