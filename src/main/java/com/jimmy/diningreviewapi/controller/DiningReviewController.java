package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.service.DiningReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dining-reviews")
public class DiningReviewController {

    private final DiningReviewService diningReviewService;

    @PostMapping
    ResponseEntity<?> submitDiningReview(@RequestBody @Valid DiningReviewRequest dto) {
        DiningReview submittedDiningReview = diningReviewService.submitDiningReview(dto);

        DiningReviewResponse response = DiningReviewResponse.toResponse(submittedDiningReview);

        return ResponseEntity.created(
                URI.create("/dining-reviews/" + submittedDiningReview.getId()))
                .body(response);
    }

    @GetMapping
    ResponseEntity<?> findApprovedReviewsOfRestaurant(@RequestParam @Positive Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewService.findApprovedReviewsOfRestaurant(restaurantId));
    }

}
