package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DiningReviewRepositoryTest {

    @Autowired
    private DiningReviewRepository diningReviewRepository;

    @ParameterizedTest
    @MethodSource
    @DisplayName("다이닝 리뷰 상태에 따른 다이닝 리뷰 페이지가 리턴되는지 테스트")
    void findAllByStatus(DiningReview.Status status, Pageable pageable) {
        // when
        Page<DiningReview> diningReviewPage = diningReviewRepository.findAllByStatus(status, pageable);

        // then
        assertThat(diningReviewPage).isNotEmpty();
        assertThat(
                diningReviewPage
                        .stream()
                        .allMatch(diningReview -> diningReview.getStatus().equals(status))
        ).isTrue();
    }

    static Stream<Arguments> findAllByStatus() {
        return Stream.of(
                Arguments.of(DiningReview.Status.WAITING, PageRequest.of(0, 10)),
                Arguments.of(DiningReview.Status.APPROVED, PageRequest.of(0, 5)),
                Arguments.of(DiningReview.Status.DENIED, PageRequest.of(0, 10))
        );
    }

    @Test
    @DisplayName("특정 레스토랑의 상태별 다이닝 리뷰 리스트 조회")
    void findAllByStatusAndRestaurant_Id() {
        // given
        Long restaurantId = 10L;
        DiningReview.Status status = DiningReview.Status.APPROVED;

        // when
        List<DiningReview> diningReviews = diningReviewRepository.findAllByStatusAndRestaurant_Id(status, restaurantId);

        // then
        assertThat(diningReviews.size()).isEqualTo(1);
        assertThat(
                diningReviews
                        .stream()
                        .allMatch(diningReview -> diningReview.getStatus().equals(status))
        ).isTrue();
    }

}