package com.jimmy.diningreviewapi.repository.querydsl.impl;

import com.jimmy.diningreviewapi.domain.entity.DiningReview;
import com.jimmy.diningreviewapi.dto.response.DiningReviewResponse;
import com.jimmy.diningreviewapi.dto.response.QDiningReviewResponse;
import com.jimmy.diningreviewapi.repository.querydsl.DiningReviewRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.jimmy.diningreviewapi.domain.entity.QDiningReview.diningReview;
import static com.jimmy.diningreviewapi.domain.entity.QMember.member;
import static com.jimmy.diningreviewapi.domain.entity.QRestaurant.restaurant;

@RequiredArgsConstructor
public class DiningReviewRepositoryCustomImpl implements DiningReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DiningReviewResponse> findByStatusQuerydsl(DiningReview.Status status, Pageable pageable) {
        List<DiningReviewResponse> content = queryFactory
                .select(new QDiningReviewResponse(
                        diningReview.id,
                        diningReview.flavorScore,
                        diningReview.priceScore,
                        diningReview.serviceScore,
                        diningReview.comment,
                        diningReview.status,
                        member.name,
                        restaurant.id
                ))
                .from(diningReview)
                .leftJoin(diningReview.member, member)
                .leftJoin(diningReview.restaurant, restaurant)
                .where(diningReview.status.eq(status))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(diningReview.count())
                .from(diningReview)
                .where(diningReview.status.eq(status));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public List<DiningReviewResponse> findByStatusAndRestaurantIdQuerydsl(DiningReview.Status status, Long restaurantId) {
        return queryFactory
                .select(new QDiningReviewResponse(
                        diningReview.id,
                        diningReview.flavorScore,
                        diningReview.priceScore,
                        diningReview.serviceScore,
                        diningReview.comment,
                        diningReview.status,
                        member.name,
                        restaurant.id
                ))
                .from(diningReview)
                .leftJoin(diningReview.member, member)
                .leftJoin(diningReview.restaurant, restaurant)
                .where(diningReview.status.eq(status),
                        restaurant.id.eq(restaurantId))
                .fetch();
    }

    @Override
    public List<DiningReview> findByMemberIdQuerydsl(Long memberId) {
        return queryFactory
                .selectFrom(diningReview)
                .join(diningReview.member, member).fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();
    }

}
