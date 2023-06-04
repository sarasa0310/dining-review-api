package com.jimmy.diningreviewapi.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public final class DiningReviewRequest {

    @NotBlank
    private String memberName;

    @Positive @NotNull
    private Long restaurantId;

    @Min(1)@Max(5)
    private Integer peanutScore;
    @Min(1)@Max(5)
    private Integer eggScore;
    @Min(1)@Max(5)
    private Integer dairyScore;

    private String comment;

}
