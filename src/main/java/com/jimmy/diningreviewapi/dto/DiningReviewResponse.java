package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.DiningReview;
import lombok.Getter;

@Getter
public class DiningReviewResponse {

    private final Long id;

    private final Integer peanutScore;
    private final Integer eggScore;
    private final Integer dairyScore;

    private final String comment;

    private final Boolean isApproved;

    private final String memberName;
    private final String restaurantName;

    private DiningReviewResponse(Long id, Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, Boolean isApproved, String memberName, String restaurantName) {
        this.id = id;
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.comment = comment;
        this.isApproved = isApproved;
        this.memberName = memberName;
        this.restaurantName = restaurantName;
    }

    public static DiningReviewResponse of(Long id, Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, Boolean isApproved, String memberName, String restaurantName) {
        return new DiningReviewResponse(id, peanutScore, eggScore, dairyScore, comment, isApproved, memberName, restaurantName);
    }

    public static DiningReviewResponse from(DiningReview entity) {
        return DiningReviewResponse.of(
                entity.getId(),
                entity.getPeanutScore(),
                entity.getEggScore(),
                entity.getDairyScore(),
                entity.getComment(),
                entity.getIsApproved(),
                entity.getMember().getName(),
                entity.getRestaurant().getName()
        );
    }

}
