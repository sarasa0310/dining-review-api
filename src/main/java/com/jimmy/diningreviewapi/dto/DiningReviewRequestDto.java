package com.jimmy.diningreviewapi.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public final class DiningReviewRequestDto {

    @NotBlank
    private String memberName;
    @NotNull
    private Long restaurantId;

    @Min(1)@Max(5)
    private Integer peanutScore;
    @Min(1)@Max(5)
    private Integer eggScore;
    @Min(1)@Max(5)
    private Integer dairyScore;

    private String comment;

}
