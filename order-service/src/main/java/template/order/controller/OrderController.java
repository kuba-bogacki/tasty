package template.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import template.order.domain.dto.OrderDto;
import template.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewOrder(@Valid @RequestBody OrderDto.Create createDto) {
        orderService.createOrder(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/deliver")
    public ResponseEntity<?> deliverOrder(@Valid @RequestBody OrderDto.Deliver deliverDto) {
        orderService.deliverOrder(deliverDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
