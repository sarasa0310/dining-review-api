package com.jimmy.diningreviewapi.repository.querydsl;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.response.MemberResponse;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByNameQuerydsl(String name);

    boolean existsByNameQuerydsl(String name);

    List<MemberResponse> findMembersByCity(String city);

}
