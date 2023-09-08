package com.jimmy.diningreviewapi.repository.querydsl;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.response.MemberResponse;
import com.jimmy.diningreviewapi.dto.response.QMemberResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.jimmy.diningreviewapi.domain.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findByNameQuerydsl(String name) {
        Member foundMember = queryFactory
                .selectFrom(member)
                .where(member.name.eq(name))
                .fetchOne();

        return Optional.ofNullable(foundMember);
    }

    @Override
    public boolean existsByNameQuerydsl(String name) {
        return queryFactory
                .select(member.id)
                .from(member)
                .where(member.name.eq(name))
                .fetchFirst() != null;
    }

    @Override
    public List<MemberResponse> findMembersByCity(String city) {
        return queryFactory
                .select(new QMemberResponse(
                        member.id, member.name, member.state, member.city, member.zipCode,
                        member.peanutAllergy, member.eggAllergy, member.dairyAllergy
                ))
                .from(member)
                .where(member.city.eq(city))
                .fetch();
    }

//    @Override
//    public List<MemberResponse> findMembersByCity(String city) {
//        return queryFactory
//                .select(Projections.constructor(MemberResponse.class,
//                        member.id, member.name, member.state, member.city, member.zipCode,
//                        member.peanutAllergy, member.eggAllergy, member.dairyAllergy))
//                .from(member)
//                .where(member.city.eq(city))
//                .fetch();
//    }

}
