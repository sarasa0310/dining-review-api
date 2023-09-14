package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.dto.request.RestaurantRequest;
import com.jimmy.diningreviewapi.dto.response.RestaurantResponse;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import com.jimmy.diningreviewapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping
    public ResponseEntity<?> submitNewRestaurant(@RequestBody @Valid RestaurantRequest dto) {
        Restaurant restaurant = dto.toEntity();

        Restaurant submittedRestaurant = restaurantService.submitNewRestaurant(restaurant);

        RestaurantResponse response = RestaurantResponse.toResponse(submittedRestaurant);

        return ResponseEntity.created(
                URI.create("/restaurants/" + response.getRestaurantId()))
                .body(response);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantInfo(@PathVariable Long restaurantId) {
        RestaurantResponse response = RestaurantResponse.toResponse(
                restaurantService.findRestaurantById(restaurantId));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getRestaurantsByZipCodeHavingScore(@RequestParam @Positive Integer zipCode) {
        return ResponseEntity.ok(
                restaurantService.findRestaurantsByZipCodeHavingScore(zipCode));
    }

    @GetMapping("/querydsl")
    public ResponseEntity<?> getRestaurantsByZipCodeHavingScore2(@RequestParam @Positive Integer zipCode) {
        return ResponseEntity.ok(
                restaurantRepository.findRestaurantsHavingScore(zipCode));
    }

    @GetMapping("/ranking")
    public ResponseEntity<?> getRestaurantsRanking(Pageable pageable) {
        return ResponseEntity.ok(
                restaurantService.findRestaurantsRanking(pageable));
    }

    @GetMapping("/ranking/querydsl")
    public ResponseEntity<?> getRestaurantsRanking2(Pageable pageable) {
        return ResponseEntity.ok(
                restaurantRepository.findRestaurantsRanking(pageable));
    }

}
