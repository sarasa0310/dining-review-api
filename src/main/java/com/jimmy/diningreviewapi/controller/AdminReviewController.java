package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping("/dining-reviews")
    ResponseEntity<?> getUnApprovedDiningReviews() {
        return ResponseEntity.ok(adminReviewService.findUnApprovedDiningReviews());
    }

}
