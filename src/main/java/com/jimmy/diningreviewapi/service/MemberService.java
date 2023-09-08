package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.request.MemberUpdate;
import com.jimmy.diningreviewapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        verifyExistingMember(member.getName());
        return memberRepository.save(member);
    }

    public Member findMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }

    public Member updateMember(String name, MemberUpdate memberUpdate) {
        Member member = findMemberByName(name);

        if (StringUtils.hasText(memberUpdate.getState())) member.setState(memberUpdate.getState());
        if (StringUtils.hasText(memberUpdate.getCity())) member.setCity(memberUpdate.getCity());
        if (memberUpdate.getZipCode() != null) member.setZipCode(memberUpdate.getZipCode());
        if (memberUpdate.getHasPeanutAllergy() != null) member.setPeanutAllergy(memberUpdate.getHasPeanutAllergy());
        if (memberUpdate.getHasEggAllergy() != null) member.setEggAllergy(memberUpdate.getHasEggAllergy());
        if (memberUpdate.getHasDairyAllergy() != null) member.setDairyAllergy(memberUpdate.getHasDairyAllergy());

        return member;
    }

    public void deleteMember(String name) {
        Member foundMember = findMemberByName(name);
        memberRepository.delete(foundMember);
    }

    private void verifyExistingMember(String name) {
        if (memberRepository.existsByName(name)) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
    }

}
