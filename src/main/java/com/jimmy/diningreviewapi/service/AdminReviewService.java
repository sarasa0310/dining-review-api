package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.dto.DiningReviewResponse;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminReviewService {

    private final DiningReviewRepository diningReviewRepository;

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findUnApprovedDiningReviews() {
        return diningReviewRepository.findAllByIsApprovedIsFalse()
                .stream()
                .map(DiningReviewResponse::from)
                .collect(Collectors.toList());
    }

}
