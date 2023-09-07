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

    @Transactional(readOnly = true)
    public Member findMemberByName(String name) {
        return findExistingMember(name);
    }

    public Member updateMember(String name, MemberUpdate memberUpdate) {
        Member foundMember = findExistingMember(name);

        if (StringUtils.hasText(memberUpdate.getState())) foundMember.setState(memberUpdate.getState());
        if (StringUtils.hasText(memberUpdate.getCity())) foundMember.setCity(memberUpdate.getCity());
        if (memberUpdate.getZipCode() != null) foundMember.setZipCode(memberUpdate.getZipCode());
        if (memberUpdate.getHasPeanutAllergy() != null) foundMember.setPeanutAllergy(memberUpdate.getHasPeanutAllergy());
        if (memberUpdate.getHasEggAllergy() != null) foundMember.setEggAllergy(memberUpdate.getHasEggAllergy());
        if (memberUpdate.getHasDairyAllergy() != null) foundMember.setDairyAllergy(memberUpdate.getHasDairyAllergy());

        return foundMember;
    }

    public void deleteMember(String name) {
        Member foundMember = findExistingMember(name);
        memberRepository.delete(foundMember);
    }

    private Member findExistingMember(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }

    private void verifyExistingMember(String name) {
        if (memberRepository.existsByName(name)) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
    }

}
