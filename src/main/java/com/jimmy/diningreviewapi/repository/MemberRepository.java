package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
    boolean existsByName(String name);

}
