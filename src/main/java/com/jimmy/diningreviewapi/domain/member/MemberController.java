package com.jimmy.diningreviewapi.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity();

        Member savedMember = memberService.saveMember(member);

        return ResponseEntity.created(
                URI.create("/members/" + savedMember.getId())).build();
    }

}
