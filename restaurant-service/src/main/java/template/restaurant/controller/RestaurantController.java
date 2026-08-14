package template.restaurant.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import template.restaurant.domain.dto.RestaurantDto;
import template.restaurant.service.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewRestaurant(@Valid @RequestBody RestaurantDto.Create createDto) {
        restaurantService.createRestaurant(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/status")
    public ResponseEntity<?> changeRestaurantStatus(@Valid @RequestBody RestaurantDto.Update updateDto) {
        restaurantService.updateRestaurantStatus(updateDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<?> withdrawPreparation(@Valid @RequestBody RestaurantDto.Withdraw withdrawDto) {
        restaurantService.withdrawPreparation(withdrawDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "/prepare")
    public ResponseEntity<?> startPreparation(@Valid @RequestBody RestaurantDto.Prepare prepareDto) {
        restaurantService.startPreparation(prepareDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "/ready")
    public ResponseEntity<?> finishPreparation(@Valid @RequestBody RestaurantDto.Ready readyDto) {
        restaurantService.finishPreparation(readyDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
