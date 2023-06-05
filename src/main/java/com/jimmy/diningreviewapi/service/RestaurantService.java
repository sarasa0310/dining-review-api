package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant submitNewRestaurant(Restaurant restaurant) {
        verifyExistingRestaurant(restaurant.getName(), restaurant.getZipCode());

        return restaurantRepository.save(restaurant);
    }

    private void verifyExistingRestaurant(String name, Integer zipCode) {
        if (restaurantRepository.existsByNameAndZipCode(name, zipCode)) {
            throw new RuntimeException("이미 등록된 레스토랑입니다.");
        }
    }

}
