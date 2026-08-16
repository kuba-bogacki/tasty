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

    @PatchMapping(value = "/start")
    public ResponseEntity<?> startPreparation(@Valid @RequestBody RestaurantDto.Start startDto) {
        restaurantService.startPreparation(startDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "/complete")
    public ResponseEntity<?> completePreparation(@Valid @RequestBody RestaurantDto.Ready readyDto) {
        restaurantService.completePreparation(readyDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
