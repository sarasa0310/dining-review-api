package com.jimmy.diningreviewapi.event.legacy;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Deprecated
@Transactional
@RequiredArgsConstructor
public class ReviewApprovedEventHandler {

    private final RestaurantService restaurantService;

//    @EventListener(ReviewApprovedEventLegacy.class)
    public void updateScore(ReviewApprovedEventLegacy event) {
        Restaurant restaurant = restaurantService.findRestaurantById(
                event.getDiningReview().getRestaurant().getId());

        restaurant.updateScore(event.getDiningReview());
    }

}
