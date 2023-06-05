package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.DiningReview;
import lombok.Getter;

@Getter
public final class DiningReviewResponse {

    private final Long id;

    private final Integer peanutScore;
    private final Integer eggScore;
    private final Integer dairyScore;

    private final String comment;

    private final boolean approved;

    private final String memberName;
    private final String restaurantName;

    private DiningReviewResponse(Long id, Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, boolean approved, String memberName, String restaurantName) {
        this.id = id;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.comment = comment;
        this.approved = approved;
        this.memberName = memberName;
        this.restaurantName = restaurantName;
    }

    public static DiningReviewResponse of(Long id, Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, boolean approved, String memberName, String restaurantName) {
        return new DiningReviewResponse(id, peanutScore, eggScore, dairyScore, comment, approved, memberName, restaurantName);
    }

    public static DiningReviewResponse from(DiningReview entity) {
        return DiningReviewResponse.of(
                entity.getId(),
                entity.getPeanutScore(),
                entity.getEggScore(),
                entity.getDairyScore(),
                entity.getComment(),
                entity.isApproved(),
                entity.getMember().getName(),
                entity.getRestaurant().getName()
        );
    }

}
