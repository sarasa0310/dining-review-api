package com.jimmy.diningreviewapi.domain.member;

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
    public Member findMember(String name) {
        return findExistingMember(name);
    }

    public Member updateMember(String name, MemberUpdateDto memberUpdateDto) {
        Member foundMember = findExistingMember(name);

        if (memberUpdateDto.getState() != null) foundMember.setState(memberUpdateDto.getState());
        if (memberUpdateDto.getCity() != null) foundMember.setCity(memberUpdateDto.getCity());
        if (memberUpdateDto.getZipCode() != null) foundMember.setZipCode(memberUpdateDto.getZipCode());
        if (memberUpdateDto.getPeanutAllergies() != null) foundMember.setPeanutAllergies(memberUpdateDto.getPeanutAllergies());
        if (memberUpdateDto.getEggAllergies() != null) foundMember.setEggAllergies(memberUpdateDto.getEggAllergies());
        if (memberUpdateDto.getDairyAllergies() != null) foundMember.setDairyAllergies(memberUpdateDto.getDairyAllergies());

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
