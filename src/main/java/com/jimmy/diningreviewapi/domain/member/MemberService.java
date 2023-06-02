package com.jimmy.diningreviewapi.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        verifyExistingMember(member.getName());
        return memberRepository.save(member);
    }

    private void verifyExistingMember(String name) {
        if (memberRepository.existsByName(name)) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
    }

}
