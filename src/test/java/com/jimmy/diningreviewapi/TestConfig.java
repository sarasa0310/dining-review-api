package com.jimmy.diningreviewapi;

import com.jimmy.diningreviewapi.repository.querydsl.MemberRepositoryCustom;
import com.jimmy.diningreviewapi.repository.querydsl.MemberRepositoryCustomImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public MemberRepositoryCustom custom(JPAQueryFactory jpaQueryFactory) {
        return new MemberRepositoryCustomImpl(jpaQueryFactory);
    }

}
