package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.request.AdminReviewAction;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.event.refactor.ReviewApprovedEvent;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminReviewService {

    private final ApplicationEventPublisher eventPublisher;

    private final DiningReviewService diningReviewService;

    private final DiningReviewRepository diningReviewRepository;

    @Transactional(readOnly = true)
    public Page<DiningReviewResponse> findWaitingDiningReviews(Pageable pageable) {
        return diningReviewRepository.findAllByStatus(DiningReview.Status.WAITING, pageable)
                .map(DiningReviewResponse::toResponse);
    }

    public DiningReviewResponse approveOrDenyDiningReview(Long diningReviewId, AdminReviewAction action) {
        DiningReview diningReview = diningReviewService.findDiningReviewById(diningReviewId);

        verifyAlreadyApproved(diningReview);
        verifyAlreadyDenied(diningReview);

        if (action.isAcceptable()) {
            diningReview.approve();
            eventPublisher.publishEvent(new ReviewApprovedEvent(diningReview));
        } else {
            diningReview.deny();
        }

        return DiningReviewResponse.toResponse(diningReview);
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
