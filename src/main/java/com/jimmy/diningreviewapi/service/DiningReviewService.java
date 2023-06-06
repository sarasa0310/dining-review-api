package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.dto.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.DiningReviewResponse;
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

        DiningReview diningReview = DiningReview.of(
                dto.getFlavorScore(), dto.getPriceScore(), dto.getServiceScore(),
                dto.getComment(),
                restaurant, member
        );

        return diningReviewRepository.save(diningReview);
    }

    @Transactional(readOnly = true)
    public List<DiningReviewResponse> findApprovedReviewsOfRestaurant(Long restaurantId) {
        return diningReviewRepository.findAllByApprovedIsTrueAndRestaurant_Id(restaurantId)
                .stream()
                .map(DiningReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DiningReview findDiningReviewById(Long diningReviewId) {
        return diningReviewRepository.findById(diningReviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당 다이닝 리뷰를 찾을 수 없습니다."));
    }

}
