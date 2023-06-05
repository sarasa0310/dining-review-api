package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.dto.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.DiningReviewResponse;
import com.jimmy.diningreviewapi.service.DiningReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dining-reviews")
public class DiningReviewController {

    private final DiningReviewService diningReviewService;

    @PostMapping
    ResponseEntity<?> submitDiningReview(@Valid @RequestBody DiningReviewRequest dto) {
        DiningReview submittedDiningReview = diningReviewService.submitDiningReview(dto);

        DiningReviewResponse response = DiningReviewResponse.from(submittedDiningReview);

        return ResponseEntity.created(
                URI.create("/dining-reviews/" + submittedDiningReview.getId()))
                .body(response);
    }

    @GetMapping
    ResponseEntity<?> findApprovedReviewsOfRestaurant(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewService.findApprovedReviewsOfRestaurant(restaurantId));
    }

}
