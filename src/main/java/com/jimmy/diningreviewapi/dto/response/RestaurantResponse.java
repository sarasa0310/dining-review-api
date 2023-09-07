package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.domain.value.Score;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantResponse {

    private final Long restaurantId;

    private final String name;
    private final Integer zipCode;

    private final Score score;
    private final double averageScore;

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getZipCode(),
                restaurant.getScore(),
                restaurant.getAverageScore()
        );
    }

}
