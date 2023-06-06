package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public final class RestaurantDto {

    @NotBlank
    private String name;
    @NotNull
    private Integer zipCode;

    public Restaurant toEntity() {
        return Restaurant.of(name, zipCode);
    }

}
