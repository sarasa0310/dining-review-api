package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {

    Page<DiningReview> findAllByStatus(DiningReview.Status status, Pageable pageable);
    List<DiningReview> findAllByStatusAndRestaurant_Id(DiningReview.Status status, Long restaurantId);

}
