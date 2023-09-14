package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.request.AdminReviewAction;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import com.jimmy.diningreviewapi.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/dining-reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;
    private final DiningReviewRepository diningReviewRepository;

    @GetMapping("/waiting")
    public ResponseEntity<?> getWaitingDiningReviews(Pageable pageable) {
        return ResponseEntity.ok(
                adminReviewService.findWaitingDiningReviews(pageable));
    }

    @GetMapping("/waiting/querydsl")
    public ResponseEntity<?> getWaitingDiningReviews2(Pageable pageable) {
        return ResponseEntity.ok(
                diningReviewRepository.findByStatusQuerydsl(DiningReview.Status.WAITING, pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> approveOrDenyDiningReview(@PathVariable("id") @Positive Long diningReviewId,
                                                       @RequestBody @Valid AdminReviewAction action) {
        return ResponseEntity.ok(
                adminReviewService.approveOrDenyDiningReview(diningReviewId, action));
    }

}
