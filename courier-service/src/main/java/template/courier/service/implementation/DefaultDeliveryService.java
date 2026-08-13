package template.courier.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import template.courier.publisher.CourierEventPublisher;
import template.courier.repository.DeliveryRepository;
import template.courier.service.CourierService;
import template.courier.service.DeliveryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultDeliveryService implements DeliveryService {

    private final CourierService courierService;
    private final DeliveryRepository deliveryRepository;
    private final CourierEventPublisher courierEventPublisher;

}
