package com.jimmy.diningreviewapi.repository.querydsl;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiningReviewRepositoryCustom {

    Page<DiningReviewResponse> findByStatusQuerydsl(DiningReview.Status status, Pageable pageable);

    List<DiningReviewResponse> findByStatusAndRestaurantIdQuerydsl(DiningReview.Status status, Long restaurantId);

    List<DiningReview> findByMemberIdQuerydsl(Long memberId);

}
