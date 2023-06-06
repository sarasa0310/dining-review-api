package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.DiningReviewResponse;
import com.jimmy.diningreviewapi.event.AdminReviewAction;
import com.jimmy.diningreviewapi.event.Events;
import com.jimmy.diningreviewapi.event.ReviewApprovedEvent;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminReviewService {

    private final DiningReviewService diningReviewService;

    private final DiningReviewRepository diningReviewRepository;

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findWaitingDiningReviews() {
        return diningReviewRepository.findAllByStatus(DiningReview.Status.WAITING)
                .stream()
                .map(DiningReviewResponse::from)
                .collect(Collectors.toList());
    }

    public DiningReviewResponse approveOrDenyDiningReview(Long diningReviewId, AdminReviewAction action) {
        DiningReview diningReview = diningReviewService.findDiningReviewById(diningReviewId);

        verifyAlreadyApproved(diningReview);
        verifyAlreadyDenied(diningReview);

        if (action.isAcceptable()) {
            diningReview.approve();
            Events.raise(new ReviewApprovedEvent(diningReview));
        } else {
            diningReview.deny();
        }

        return DiningReviewResponse.from(diningReview);
    }

    private void verifyAlreadyApproved(DiningReview diningReview) {
        if (diningReview.getStatus().equals(DiningReview.Status.APPROVED)) {
            throw new RuntimeException("이미 승인된 다이닝 리뷰입니다.");
        }
    }

    private void verifyAlreadyDenied(DiningReview diningReview) {
        if (diningReview.getStatus().equals(DiningReview.Status.DENIED)) {
            throw new RuntimeException("이미 거절된 다이닝 리뷰입니다.");
        }
    }

}
