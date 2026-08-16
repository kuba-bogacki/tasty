package template.courier.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import template.courier.domain.dto.CourierDto;
import template.courier.domain.dto.DeliveryDto;
import template.courier.service.CourierService;
import template.courier.service.DeliveryService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/courier")
public class CourierController {

    private final CourierService courierService;
    private final DeliveryService deliveryService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewCourier(@Valid @RequestBody CourierDto.Create createDto) {
        courierService.createNewCourier(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/send")
    public ResponseEntity<?> sendDelivery(@Valid @RequestBody DeliveryDto.Send sendDto) {
        deliveryService.sendDelivery(sendDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
