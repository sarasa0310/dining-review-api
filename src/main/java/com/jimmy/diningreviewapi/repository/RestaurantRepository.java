package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.repository.querydsl.RestaurantRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends
        JpaRepository<Restaurant, Long>,
        RestaurantRepositoryCustom {

    boolean existsByNameAndZipCode(String name, Integer zipCode);
    List<Restaurant> findByZipCodeOrderByIdDesc(Integer zipCode);
    Page<Restaurant> findByOrderByAverageScoreDesc(Pageable pageable);

}
