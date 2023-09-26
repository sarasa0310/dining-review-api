package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.request.SignUpRequest;
import com.jimmy.diningreviewapi.dto.response.MemberResponse;
import com.jimmy.diningreviewapi.repository.MemberRepository;
import com.jimmy.diningreviewapi.service.MemberService;
import com.jimmy.diningreviewapi.dto.request.MemberUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        Member member = signUpRequest.toEntity();

        Member savedMember = memberService.saveMember(member);

        return ResponseEntity.created(
                URI.create("/members/" + savedMember.getId()))
                .body(MemberResponse.toResponse(savedMember));
    }

    @GetMapping
    public ResponseEntity<?> getProfile(@RequestParam String name) {
        Member foundMember = memberService.findMemberByName(name);

        return ResponseEntity.ok(
                MemberResponse.toResponse(foundMember));
    }

    // QueryDSL 사용 방식
    @GetMapping("/search")
    public ResponseEntity<?> searchByCity(@RequestParam String city) {
        return ResponseEntity.ok(
                memberRepository.findMembersByCity(city));
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestParam String name,
                                           @RequestBody @Valid MemberUpdate memberUpdate) {
        Member updatedMember = memberService.updateMember(name, memberUpdate);

        return ResponseEntity.ok(
                MemberResponse.toResponse(updatedMember));
    }

    @DeleteMapping
    public ResponseEntity<?> leave(@RequestParam String name) {
        memberService.deleteMember(name);

        return ResponseEntity.noContent().build();
    }

}
