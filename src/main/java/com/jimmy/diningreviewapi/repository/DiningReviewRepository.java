package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.DiningReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    List<DiningReview> findAllByApprovedIsFalse();

    List<DiningReview> findAllByApprovedIsTrueAndRestaurant_Id(Long restaurantId);

}
