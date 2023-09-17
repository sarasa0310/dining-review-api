package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.request.DiningReviewSearch;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import com.jimmy.diningreviewapi.service.DiningReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

        return ResponseEntity.created(
                URI.create("/dining-reviews/" + submittedDiningReview.getId()))
                .body(DiningReviewResponse.toResponse(submittedDiningReview));
    }

    // 기존 메서드
    @GetMapping("/approved")
    public ResponseEntity<?> getApprovedReviewsOfRestaurant(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewService.findApprovedReviewsOfRestaurant(restaurantId));
    }

    // QueryDSL 사용 방식
    @GetMapping("/approved/querydsl")
    public ResponseEntity<?> getApprovedReviewsOfRestaurant2(@RequestParam Long restaurantId) {
        return ResponseEntity.ok(
                diningReviewRepository.findByStatusAndRestaurantIdQuerydsl(DiningReview.Status.APPROVED, restaurantId));
    }

    @GetMapping
    public ResponseEntity<?> findReviewsOfMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(
                diningReviewService.findReviewsOfMember(memberId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDiningReviews(DiningReviewSearch search, Pageable pageable) {
        return ResponseEntity.ok(
                diningReviewRepository.searchDiningReviews(search, pageable));
    }

}
