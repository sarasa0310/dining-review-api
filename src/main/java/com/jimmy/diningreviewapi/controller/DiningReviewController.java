package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
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
    private final DiningReviewRepository diningReviewRepository;

    @PostMapping
    public ResponseEntity<?> submitDiningReview(@RequestBody @Valid DiningReviewRequest dto) {
        DiningReview submittedDiningReview = diningReviewService.submitDiningReview(dto);

        DiningReviewResponse response = DiningReviewResponse.toResponse(submittedDiningReview);

        return ResponseEntity.created(
                URI.create("/dining-reviews/" + response.getDiningReviewId()))
                .body(response);
    }

    @GetMapping("/approved")
    public ResponseEntity<?> findApprovedReviewsOfRestaurant(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewService.findApprovedReviewsOfRestaurant(restaurantId));
    }

    @GetMapping("/approved/querydsl")
    public ResponseEntity<?> findApprovedReviewsOfRestaurant2(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewRepository.findByStatusAndRestaurantIdQuerydsl(DiningReview.Status.APPROVED, restaurantId));
    }

    @GetMapping
    public ResponseEntity<?> findReviewsOfMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(
                diningReviewService.findReviewsOfMember(memberId));
    }

}
