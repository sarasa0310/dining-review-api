package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.dto.RestaurantDto;
import com.jimmy.diningreviewapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    ResponseEntity<?> submitNewRestaurant(@RequestBody @Valid RestaurantDto dto) {
        Restaurant restaurant = dto.toEntity();

        Restaurant submittedRestaurant = restaurantService.submitNewRestaurant(restaurant);

        return ResponseEntity.created(
                URI.create("/restaurants/" + submittedRestaurant.getId()))
                .body(submittedRestaurant);
    }

}
