package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.dto.DiningReviewResponse;
import com.jimmy.diningreviewapi.event.AdminReviewAction;
import com.jimmy.diningreviewapi.event.Events;
import com.jimmy.diningreviewapi.event.ReviewApprovedEvent;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminReviewService {

    private final DiningReviewRepository diningReviewRepository;

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findUnApprovedDiningReviews() {
        return diningReviewRepository.findAllByIsApprovedIsFalse()
                .stream()
                .map(DiningReviewResponse::from)
                .collect(Collectors.toList());
    }

    public DiningReviewResponse approveDiningReview(Long diningReviewId, AdminReviewAction action) {
        DiningReview diningReview =
                diningReviewRepository.findById(diningReviewId)
                        .orElseThrow(() -> new EntityNotFoundException("해당 다이닝 리뷰를 찾을 수 없습니다."));

        if (!diningReview.getIsApproved() && action.getIsAcceptable()) {
            diningReview.approve();
            Events.raise(new ReviewApprovedEvent(diningReview));
        }

        return DiningReviewResponse.from(diningReview);
    }

}
