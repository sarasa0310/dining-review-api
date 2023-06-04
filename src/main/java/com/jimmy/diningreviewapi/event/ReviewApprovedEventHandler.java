package com.jimmy.diningreviewapi.event;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewApprovedEventHandler {

    private final RestaurantRepository restaurantRepository;

    @EventListener(ReviewApprovedEvent.class)
    public void updateScore(ReviewApprovedEvent event) {
        Restaurant restaurant = restaurantRepository.findById(
                event.getDiningReview().getRestaurant().getId())
                .orElseThrow();

        restaurant.updateScore(event.getDiningReview());
    }

}
