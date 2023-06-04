package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.DiningReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    List<DiningReview> findAllByIsApprovedIsFalse();

    List<DiningReview> findAllByRestaurant_IdAndIsApprovedIsTrue(Long restaurantId);

}
