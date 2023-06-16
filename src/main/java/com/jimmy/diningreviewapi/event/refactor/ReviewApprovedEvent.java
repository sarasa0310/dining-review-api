package com.jimmy.diningreviewapi.event.refactor;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewApprovedEvent {

    private final DiningReview diningReview;

}
