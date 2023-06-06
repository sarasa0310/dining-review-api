package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.DiningReview;
import lombok.Getter;

@Getter
public final class DiningReviewResponse {

    private final Long id;

    private final Integer flavorScore;
    private final Integer priceScore;
    private final Integer serviceScore;

    private final String comment;

    private final boolean approved;

    private final String memberName;
    private final String restaurantName;

    private DiningReviewResponse(Long id, Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, boolean approved, String memberName, String restaurantName) {
        this.id = id;
        this.flavorScore = flavorScore;
        this.priceScore = priceScore;
        this.serviceScore = serviceScore;
        this.comment = comment;
        this.approved = approved;
        this.memberName = memberName;
        this.restaurantName = restaurantName;
    }

    public static DiningReviewResponse of(Long id, Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, boolean approved, String memberName, String restaurantName) {
        return new DiningReviewResponse(id, flavorScore, priceScore, serviceScore, comment, approved, memberName, restaurantName);
    }

    public static DiningReviewResponse from(DiningReview entity) {
        return DiningReviewResponse.of(
                entity.getId(),
                entity.getFlavorScore(),
                entity.getPriceScore(),
                entity.getServiceScore(),
                entity.getComment(),
                entity.isApproved(),
                entity.getMember().getName(),
                entity.getRestaurant().getName()
        );
    }

}
