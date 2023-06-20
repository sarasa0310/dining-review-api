package com.jimmy.diningreviewapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.domain.entity.Restaurant;
import com.jimmy.diningreviewapi.dto.request.DiningReviewRequest;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.service.DiningReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiningReviewController.class)
class DiningReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DiningReviewService diningReviewService;

    @Test
    @DisplayName("미승인 다이닝 리뷰 제출 테스트")
    void submitDiningReview() throws Exception {
        // given
        Member member = Member.of(
                "Willdon Piola", "New Mexico", "Albuquerque", 23625, false, false, false);
        Restaurant restaurant = Restaurant.of(
                "Becker-Runolfsson", 10510);

        DiningReviewRequest request = new DiningReviewRequest(
                "Willdon Piola", 1L, 5, 5, 5, "good!");

        DiningReview diningReview = request.toEntity(restaurant, member);
        given(diningReviewService.submitDiningReview(any(DiningReviewRequest.class)))
                .willReturn(diningReview);

        DiningReviewResponse response = DiningReviewResponse.from(diningReview);

        // when
        ResultActions actions =
                mvc.perform(
                        post("/dining-reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)));

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberName").value(response.getMemberName()))
                .andExpect(jsonPath("$.restaurantName").value(response.getRestaurantName()));
    }

    @Test
    @DisplayName("특정 레스토랑의 승인된 다이닝 리뷰 조회 테스트")
    void findApprovedReviewsOfRestaurant() throws Exception {
        // given
        Long restaurantId = 1L;

        List<DiningReviewResponse> responses =
                List.of(
                        new DiningReviewResponse(1L, 1, 1, 1, null, DiningReview.Status.APPROVED, "jimmy", "red"),
                        new DiningReviewResponse(2L, 1, 3, 5, null, DiningReview.Status.APPROVED, "kim", "blue"),
                        new DiningReviewResponse(3L, 5, 5, 5, null, DiningReview.Status.APPROVED, "jessie", "while")
                );

        given(diningReviewService.findApprovedReviewsOfRestaurant(anyLong()))
                .willReturn(responses);

        // when
        ResultActions actions =
                mvc.perform(
                        get("/dining-reviews")
                                .queryParam("restaurantId", String.valueOf(restaurantId))
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(responses.get(0).getId()))
                .andExpect(jsonPath("$[1].memberName").value(responses.get(1).getMemberName()))
                .andExpect(jsonPath("$[2].restaurantName").value(responses.get(2).getRestaurantName()));
    }

}