package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.querydsl.core.annotations.QueryProjection;
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
    private final Long restaurantId;

    @QueryProjection
    public DiningReviewResponse(Long diningReviewId, Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, DiningReview.Status status, String memberName, Long restaurantId) {
        this.diningReviewId = diningReviewId;
        this.flavorScore = flavorScore;
        this.priceScore = priceScore;
        this.serviceScore = serviceScore;
        this.comment = comment;
        this.status = status;
        this.memberName = memberName;
        this.restaurantId = restaurantId;
    }

    public static DiningReviewResponse toResponse(DiningReview diningReview) {
        return new DiningReviewResponse(
                diningReview.getId(),
                diningReview.getFlavorScore(),
                diningReview.getPriceScore(),
                diningReview.getServiceScore(),
                diningReview.getComment(),
                diningReview.getStatus(),
                diningReview.getMember().getName(),
                diningReview.getRestaurant().getId());
    }

}
