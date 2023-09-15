package com.jimmy.diningreviewapi.repository.querydsl.impl;

import com.jimmy.diningreviewapi.dto.response.QRestaurantResponse;
import com.jimmy.diningreviewapi.dto.response.RestaurantResponse;
import com.jimmy.diningreviewapi.repository.querydsl.RestaurantRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.jimmy.diningreviewapi.domain.entity.QRestaurant.restaurant;

@RequiredArgsConstructor
public class RestaurantRepositoryCustomImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByNameAndZipCodeQuerydsl(String name, Integer zipCode) {
        return queryFactory
                .select(restaurant.id)
                .from(restaurant)
                .where(restaurant.name.eq(name),
                        restaurant.zipCode.eq(zipCode))
                .fetchFirst() != null;
    }

    @Override
    public List<RestaurantResponse> findRestaurantsHavingScore(Integer zipCode) {
        return queryFactory
                .select(new QRestaurantResponse(
                        restaurant.id, restaurant.name, restaurant.zipCode, restaurant.score, restaurant.averageScore))
                .from(restaurant)
                .where(restaurant.averageScore.gt(0),
                        restaurant.zipCode.eq(zipCode))
                .fetch();
    }

    @Override
    public Page<RestaurantResponse> findRestaurantsRanking(Pageable pageable) {
        List<RestaurantResponse> content = queryFactory
                .select(new QRestaurantResponse(
                        restaurant.id, restaurant.name, restaurant.zipCode, restaurant.score, restaurant.averageScore))
                .from(restaurant)
                .orderBy(restaurant.averageScore.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(restaurant.count())
                .from(restaurant);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

}
