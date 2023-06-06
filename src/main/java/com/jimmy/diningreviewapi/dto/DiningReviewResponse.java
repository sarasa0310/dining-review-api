package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import lombok.Getter;

@Getter
public final class DiningReviewResponse {

    private final Long id;

    private final Integer flavorScore;
    private final Integer priceScore;
    private final Integer serviceScore;

    private final String comment;

    private final DiningReview.Status status;

    private final String memberName;
    private final String restaurantName;

    public DiningReviewResponse(Long id, Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, DiningReview.Status status, String memberName, String restaurantName) {
        this.id = id;
        this.flavorScore = flavorScore;
        this.priceScore = priceScore;
        this.serviceScore = serviceScore;
        this.comment = comment;
        this.status = status;
        this.memberName = memberName;
        this.restaurantName = restaurantName;
    }

    public static DiningReviewResponse of(Long id, Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, DiningReview.Status status, String memberName, String restaurantName) {
        return new DiningReviewResponse(id, flavorScore, priceScore, serviceScore, comment, status, memberName, restaurantName);
    }

    public static DiningReviewResponse from(DiningReview entity) {
        return DiningReviewResponse.of(
                entity.getId(),
                entity.getFlavorScore(),
                entity.getPriceScore(),
                entity.getServiceScore(),
                entity.getComment(),
                entity.getStatus(),
                entity.getMember().getName(),
                entity.getRestaurant().getName()
        );
    }

}
