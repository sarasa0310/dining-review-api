package com.jimmy.diningreviewapi.event.legacy;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import lombok.Getter;

@Getter
@Deprecated
public class ReviewApprovedEventLegacy {

    private final DiningReview diningReview;

    public ReviewApprovedEventLegacy(DiningReview diningReview) {
        this.diningReview = diningReview;
    }

}
