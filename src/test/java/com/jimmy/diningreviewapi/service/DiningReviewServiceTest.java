package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.repository.DiningReviewRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DiningReviewServiceTest {

    @Mock
    private MemberService memberService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private DiningReviewRepository diningReviewRepository;

    @InjectMocks
    private DiningReviewService sut;

    private static Member member;
    private static Restaurant restaurant;

    @BeforeAll
    static void initMemberAndRestaurant() {
        member = Member.of(
                "Willdon Piola", "New Mexico", "Albuquerque", 23625, false, false, false);
        restaurant = Restaurant.of(
                "Becker-Runolfsson", 10510);
    }

    @Test
    @DisplayName("회원 이름과 레스토랑 id를 통해 해당 회원과 레스토랑의 정보를 찾아온 뒤, " +
            "해당 정보를 이용해 다이닝 리뷰를 db에 저장하는지 테스트")
    void submitDiningReview() {
        // given
        given(memberService.findMemberByName(anyString()))
                .willReturn(member);
        given(restaurantService.findRestaurantById(anyLong()))
                .willReturn(restaurant);

        DiningReviewRequest request = new DiningReviewRequest(
                member.getName(),1L, 5, 5, 5, null);
        DiningReview diningReview = request.toEntity(restaurant, member);

        given(diningReviewRepository.save(any(DiningReview.class)))
                .willReturn(diningReview);

        // when
        DiningReview submittedDiningReview = sut.submitDiningReview(request);

        // then
        assertThat(submittedDiningReview.getMember().getName()).isEqualTo(member.getName());
        assertThat(submittedDiningReview.getRestaurant().getName()).isEqualTo(restaurant.getName());
    }

    @Test
    @DisplayName("특정 레스토랑의 승인된 다이닝 리뷰 리스트 조회 테스트")
    void findApprovedReviewsOfRestaurant() {
        // given
        Long restaurantId = 1L;

        List<DiningReview> reviews = List.of(
                DiningReview.of(1, 1, 1, null, restaurant, member),
                DiningReview.of(1, 3, 5, null, restaurant, member),
                DiningReview.of(5, 5, 5, null, restaurant, member)
        );

        given(diningReviewRepository.findAllByStatusAndRestaurant_Id(any(DiningReview.Status.APPROVED.getDeclaringClass()), anyLong()))
                .willReturn(reviews);

        List<DiningReviewResponse> responses =
                reviews.stream()
                        .map(DiningReviewResponse::from)
                        .collect(Collectors.toList());

        // when
        List<DiningReviewResponse> approvedReviewsOfRestaurant = sut.findApprovedReviewsOfRestaurant(restaurantId);

        // then
        assertThat(approvedReviewsOfRestaurant).hasSize(responses.size());
        assertThat(approvedReviewsOfRestaurant
                .stream()
                .allMatch(diningReviewResponse -> diningReviewResponse.getComment() == null)).isTrue();
    }

    @Test
    @DisplayName("다이닝 리뷰 id를 가지고 해당 id를 갖는 다이닝 리뷰 조회 테스트")
    void findDiningReviewById() {
        // given
        Long diningReviewId = 1L;

        DiningReview diningReview = DiningReview.of(
                1, 1, 1, "It tastes like shit!", restaurant, member);

        given(diningReviewRepository.findById(anyLong()))
                .willReturn(Optional.of(diningReview));

        // when
        DiningReview actual = sut.findDiningReviewById(diningReviewId);

        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(DiningReview.class);
        assertThat(actual.getComment()).isEqualTo(diningReview.getComment());
    }

    @Test
    @DisplayName("존재하지 않는 다이닝 리뷰 id를 가지고 조회할 시 예외를 던지는지 테스트")
    void findDiningReviewByWrongId() {
        // given
        Long diningReviewId = 0L;

        given(diningReviewRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> sut.findDiningReviewById(diningReviewId))
                .isInstanceOf(EntityNotFoundException.class);
    }

}