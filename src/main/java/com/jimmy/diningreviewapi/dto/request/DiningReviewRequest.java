package com.jimmy.diningreviewapi.dto.request;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
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

    public DiningReview toEntity(Restaurant restaurant, Member member) {
        return DiningReview.of(
                flavorScore,
                priceScore,
                serviceScore,
                comment,
                restaurant,
                member
        );
    }

}
