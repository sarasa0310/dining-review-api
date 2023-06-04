package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.DiningReview;
import com.jimmy.diningreviewapi.domain.Member;
import com.jimmy.diningreviewapi.domain.Restaurant;
import com.jimmy.diningreviewapi.dto.DiningReviewRequestDto;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import com.jimmy.diningreviewapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class DiningReviewService {

    private final MemberService memberService;

    private final RestaurantRepository restaurantRepository;

    private final DiningReviewRepository diningReviewRepository;

    public DiningReview submitDiningReview(DiningReviewRequestDto dto) {
        Member member = memberService.findMember(dto.getMemberName());

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("해당 레스토랑은 존재하지 않습니다."));

        DiningReview diningReview = DiningReview.of(
                dto.getPeanutScore(), dto.getEggScore(), dto.getDairyScore(),
                dto.getComment(),
                restaurant, member
        );

        return diningReviewRepository.save(diningReview);
    }

}
