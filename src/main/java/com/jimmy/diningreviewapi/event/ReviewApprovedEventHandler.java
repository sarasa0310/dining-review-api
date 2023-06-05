package com.jimmy.diningreviewapi.event;

import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewApprovedEventHandler {

    private final RestaurantService restaurantService;

    @EventListener(ReviewApprovedEvent.class)
    public void updateScore(ReviewApprovedEvent event) {
        Restaurant restaurant = restaurantService.findRestaurantById(
                event.getDiningReview().getRestaurant().getId());

        restaurant.updateScore(event.getDiningReview());
    }

}
