package com.jimmy.diningreviewapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantSearch {

    private String restaurantName;
    private Integer restaurantZipCode;
    private Integer avgScoreGoe;
    private Integer avgScoreLoe;

}
