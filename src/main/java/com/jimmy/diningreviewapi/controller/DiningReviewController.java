package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.dto.DiningReviewRequestDto;
import com.jimmy.diningreviewapi.service.DiningReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dining-reviews")
public class DiningReviewController {

    private final DiningReviewService diningReviewService;

    @PostMapping
    ResponseEntity<?> submitDiningReview(@Valid @RequestBody DiningReviewRequestDto dto) {
        DiningReview submittedDiningReview = diningReviewService.submitDiningReview(dto);

        return ResponseEntity.created(
                URI.create("/dining-reviews/" + submittedDiningReview.getId()))
                .body(submittedDiningReview);
    }

}
