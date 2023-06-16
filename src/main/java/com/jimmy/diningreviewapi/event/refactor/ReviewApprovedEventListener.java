package com.jimmy.diningreviewapi.event.refactor;

import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewApprovedEventListener {

    private final RestaurantService restaurantService;

    @TransactionalEventListener(ReviewApprovedEvent.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateScore(ReviewApprovedEvent event) {
        Restaurant restaurant = restaurantService.findRestaurantById(
                event.getDiningReview().getRestaurant().getId()
        );
        restaurant.updateScore(event.getDiningReview());

        log.info(String.format(
                "Id:%d %s 레스토랑의 점수가 업데이트 되었습니다.",
                restaurant.getId(), restaurant.getName()
        ));
    }

}
