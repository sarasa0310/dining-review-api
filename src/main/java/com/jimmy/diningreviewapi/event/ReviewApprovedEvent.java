package com.jimmy.diningreviewapi.event;

import com.jimmy.diningreviewapi.domain.DiningReview;
import lombok.Getter;

@Getter
public class ReviewApprovedEvent {

    private final DiningReview diningReview;

    public ReviewApprovedEvent(DiningReview diningReview) {
        this.diningReview = diningReview;
    }

}
