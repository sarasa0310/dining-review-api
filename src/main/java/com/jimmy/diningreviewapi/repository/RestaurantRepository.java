package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    boolean existsByNameAndZipCode(String name, Integer zipCode);
    List<Restaurant> findAllByZipCodeOrderByIdDesc(Integer zipCode);
    Page<Restaurant> findAllByOrderByAverageScoreDesc(Pageable pageable);

}
