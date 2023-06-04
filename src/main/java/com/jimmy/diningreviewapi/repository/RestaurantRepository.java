package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
