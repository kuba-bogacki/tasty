package template.courier.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import template.courier.domain.dto.CourierDto;
import template.courier.service.CourierService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/courier")
public class CourierController {

    private final CourierService courierService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewCourier(@Valid @RequestBody CourierDto.Create createDto) {
        courierService.createNewCourier(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
