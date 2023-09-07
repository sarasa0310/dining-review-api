package com.jimmy.diningreviewapi.dto.request;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public final class RestaurantRequest {

    @NotBlank
    private String name;
    @Positive @NotNull
    private Integer zipCode;

    public Restaurant toEntity() {
        return new Restaurant(name, zipCode);
    }

}
