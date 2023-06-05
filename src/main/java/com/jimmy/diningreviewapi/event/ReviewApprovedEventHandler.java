package com.jimmy.diningreviewapi.event;

import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewApprovedEventHandler {

    private final RestaurantRepository restaurantRepository;

    // todo: RestaurantService 로 이동?
    @EventListener(ReviewApprovedEvent.class)
    public void updateScore(ReviewApprovedEvent event) {
        // todo: restaurantService 메서드로 교체
        Restaurant restaurant = restaurantRepository.findById(
                event.getDiningReview().getRestaurant().getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 레스토랑은 존재하지 않습니다."));

        restaurant.updateScore(event.getDiningReview());
    }

}
