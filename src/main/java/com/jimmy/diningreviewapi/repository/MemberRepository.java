package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.repository.querydsl.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends
        JpaRepository<Member, Long>,
        MemberRepositoryCustom {

    Optional<Member> findByName(String name);
    boolean existsByName(String name);

}
