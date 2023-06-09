package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.dto.request.AdminReviewAction;
import com.jimmy.diningreviewapi.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/dining-reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping
    ResponseEntity<?> getWaitingDiningReviews(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(
                adminReviewService.findWaitingDiningReviews(pageable));
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> approveOrDenyDiningReview(@PathVariable("id") @Positive Long diningReviewId,
                                                @RequestBody @Valid AdminReviewAction action) {
        return ResponseEntity.ok(
                adminReviewService.approveOrDenyDiningReview(diningReviewId, action));
    }

}
