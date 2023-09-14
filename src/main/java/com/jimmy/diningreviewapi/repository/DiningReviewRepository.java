package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.repository.querydsl.DiningReviewRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningReviewRepository extends
        JpaRepository<DiningReview, Long>,
        DiningReviewRepositoryCustom {

    @EntityGraph(attributePaths = {"member", "restaurant"})
    Page<DiningReview> findByStatus(DiningReview.Status status, Pageable pageable);

    @EntityGraph(attributePaths = {"member", "restaurant"})
    List<DiningReview> findByStatusAndRestaurant_Id(DiningReview.Status status, Long restaurantId);

    @EntityGraph(attributePaths = {"member"})
    List<DiningReview> findByMember_Id(Long memberId);

}
