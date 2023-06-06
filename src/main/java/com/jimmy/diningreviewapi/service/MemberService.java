package com.jimmy.diningreviewapi.service;

import com.jimmy.diningreviewapi.domain.Member;
import com.jimmy.diningreviewapi.dto.MemberUpdateDto;
import com.jimmy.diningreviewapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Member updateMember(String name, MemberUpdateDto memberUpdateDto) {
        Member foundMember = findExistingMember(name);

        if (memberUpdateDto.getState() != null) foundMember.setState(memberUpdateDto.getState());
        if (memberUpdateDto.getCity() != null) foundMember.setCity(memberUpdateDto.getCity());
        if (memberUpdateDto.getZipCode() != null) foundMember.setZipCode(memberUpdateDto.getZipCode());
        if (memberUpdateDto.getHasPeanutAllergies() != null) foundMember.setHasPeanutAllergies(memberUpdateDto.getHasPeanutAllergies());
        if (memberUpdateDto.getHasEggAllergies() != null) foundMember.setHasEggAllergies(memberUpdateDto.getHasEggAllergies());
        if (memberUpdateDto.getHasDairyAllergies() != null) foundMember.setHasDairyAllergies(memberUpdateDto.getHasDairyAllergies());

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
