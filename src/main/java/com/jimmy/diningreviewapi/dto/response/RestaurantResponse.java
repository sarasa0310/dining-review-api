package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.domain.value.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantResponse {

    private final Long id;

    private final String name;
    private final Integer zipCode;

    private final Score score;
    private final double averageScore;

    public static RestaurantResponse from(Restaurant entity) {
        return new RestaurantResponse(
                entity.getId(),
                entity.getName(),
                entity.getZipCode(),
                entity.getScore(),
                entity.getAverageScore()
        );
    }

}
