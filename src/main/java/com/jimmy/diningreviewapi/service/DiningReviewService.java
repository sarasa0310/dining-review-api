package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiningReviewService {

    private final MemberService memberService;
    private final RestaurantService restaurantService;
    private final DiningReviewRepository diningReviewRepository;

    public DiningReview submitDiningReview(DiningReviewRequest dto) {
        Member member = memberService.findMemberByName(dto.getMemberName());
        Restaurant restaurant = restaurantService.findRestaurantById(dto.getRestaurantId());

        DiningReview diningReview = dto.toEntity(restaurant, member);

        return diningReviewRepository.save(diningReview);
    }

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findApprovedReviewsOfRestaurant(Long restaurantId) {
        return diningReviewRepository.findByStatusAndRestaurant_Id(DiningReview.Status.APPROVED, restaurantId)
                .stream()
                .map(DiningReviewResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findReviewsOfMember(Long memberId) {
        return diningReviewRepository.findByMemberIdQuerydsl(memberId)
                .stream()
                .map(DiningReviewResponse::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DiningReview findDiningReviewById(Long diningReviewId) {
        return diningReviewRepository.findById(diningReviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당 다이닝 리뷰를 찾을 수 없습니다."));
    }

}
