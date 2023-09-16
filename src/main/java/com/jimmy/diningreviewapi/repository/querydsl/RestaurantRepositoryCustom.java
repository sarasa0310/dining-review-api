package com.jimmy.diningreviewapi.repository.querydsl;

import com.jimmy.diningreviewapi.dto.response.RestaurantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantRepositoryCustom {

    boolean existsByNameAndZipCodeQuerydsl(String name, Integer zipCode);

    List<RestaurantResponse> findRestaurantsHavingScore(Integer zipCode);

    Page<RestaurantResponse> findRestaurantsRanking(Pageable pageable);

}
