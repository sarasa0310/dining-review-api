package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    List<DiningReview> findAllByStatus(DiningReview.Status status);
    List<DiningReview> findAllByStatusAndRestaurant_Id(DiningReview.Status status, Long restaurantId);

}
