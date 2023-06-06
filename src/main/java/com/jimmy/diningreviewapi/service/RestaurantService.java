package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant submitNewRestaurant(Restaurant restaurant) {
        verifyExistingRestaurant(restaurant.getName(), restaurant.getZipCode());

        return restaurantRepository.save(restaurant);
    }

    @Transactional(readOnly = true)
    public Restaurant findRestaurantById(Long restaurantId) {
        return findExistingRestaurant(restaurantId);
    }

    @Transactional(readOnly = true)
    public List<Restaurant> findRestaurantsByZipCodeHavingAllergyScore(Integer zipCode) {
        return restaurantRepository.findAllByZipCodeOrderByIdDesc(zipCode)
                .stream()
                .filter(restaurant -> restaurant.getAverageScore() > 0)
                .collect(Collectors.toList());
    }

    private Restaurant findExistingRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 레스토랑입니다."));
    }

    private void verifyExistingRestaurant(String name, Integer zipCode) {
        if (restaurantRepository.existsByNameAndZipCode(name, zipCode)) {
            throw new RuntimeException("이미 등록된 레스토랑입니다.");
        }
    }

}
