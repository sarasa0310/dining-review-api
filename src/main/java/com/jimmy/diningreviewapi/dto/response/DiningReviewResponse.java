package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiningReviewResponse {

    private final Long diningReviewId;

    private final Integer flavorScore;
    private final Integer priceScore;
    private final Integer serviceScore;

    private final String comment;

    private final DiningReview.Status status;

    private final String memberName;
    private final String restaurantName;

    public static DiningReviewResponse toResponse(DiningReview diningReview) {
        return new DiningReviewResponse(
                diningReview.getId(),
                diningReview.getFlavorScore(),
                diningReview.getPriceScore(),
                diningReview.getServiceScore(),
                diningReview.getComment(),
                diningReview.getStatus(),
                diningReview.getMember().getName(),
                diningReview.getRestaurant().getName());
    }

}
