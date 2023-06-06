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
    private Integer flavorScore;
    @Min(1)@Max(5)
    private Integer priceScore;
    @Min(1)@Max(5)
    private Integer serviceScore;

    private String comment;

}
