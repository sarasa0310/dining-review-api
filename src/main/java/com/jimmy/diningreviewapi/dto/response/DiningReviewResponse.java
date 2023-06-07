package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DiningReviewResponse {

    private final Long id;

    private final Integer flavorScore;
    private final Integer priceScore;
    private final Integer serviceScore;

    private final String comment;

    private final DiningReview.Status status;

    private final String memberName;
    private final String restaurantName;

    public static DiningReviewResponse from(DiningReview entity) {
        return new DiningReviewResponse(
                entity.getId(),
                entity.getFlavorScore(),
                entity.getPriceScore(),
                entity.getServiceScore(),
                entity.getComment(),
                entity.getStatus(),
                entity.getMember().getName(),
                entity.getRestaurant().getName());
    }

}
