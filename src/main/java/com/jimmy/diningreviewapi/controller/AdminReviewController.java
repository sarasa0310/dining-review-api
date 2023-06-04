package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.event.AdminReviewAction;
import com.jimmy.diningreviewapi.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping("/dining-reviews")
    ResponseEntity<?> getUnApprovedDiningReviews() {
        return ResponseEntity.ok(
                adminReviewService.findUnApprovedDiningReviews());
    }

    @PatchMapping("/dining-reviews/{id}")
    ResponseEntity<?> approveDiningReview(@PathVariable("id") @Positive Long diningReviewId,
                                          @RequestBody @Valid AdminReviewAction action) {
        return ResponseEntity.ok(
                adminReviewService.approveDiningReview(diningReviewId, action));
    }

}
