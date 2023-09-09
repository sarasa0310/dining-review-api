package com.jimmy.diningreviewapi.repository.querydsl;

import com.jimmy.diningreviewapi.TestConfig;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.response.MemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestConfig.class)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class MemberRepositoryCustomTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepositoryCustom custom;

    @BeforeEach
    void setUp() {
        Member member1 = new Member("member1", "korea", "seoul", 10000, true, true, true);
        Member member2 = new Member("member2", "korea", "seoul", 10000, false, true, false);
        Member member3 = new Member("member3", "korea", "seoul", 10000, true, true, true);
        Member member4 = new Member("member4", "korea", "busan", 20000, false, true, false);
        Member member5 = new Member("member5", "korea", "daegu", 30000, true, true, true);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);
    }

    @Test
    void findByNameQuerydsl() {
        Member result1 = custom.findByNameQuerydsl("member1").get();
        Optional<Member> result2 = custom.findByNameQuerydsl("member6");

        assertThat(result1.getName()).isEqualTo("member1");
        assertThat(result2).isEmpty();
    }

    @Test
    void existsByNameQuerydsl() {
        boolean result1 = custom.existsByNameQuerydsl("member5");
        boolean result2 = custom.existsByNameQuerydsl("member6");

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }

    @Test
    void findMembersByCity() {
        List<MemberResponse> result1 = custom.findMembersByCity("seoul");
        List<MemberResponse> result2 = custom.findMembersByCity("daegu");
        List<MemberResponse> result3 = custom.findMembersByCity("mars");

        assertThat(result1).hasSize(3);
        assertThat(result2).hasSize(1);
        assertThat(result3).isEmpty();

        assertThat(result1).extracting("city").contains("seoul");
        assertThat(result2).extracting("city").containsExactly("daegu");
    }

}