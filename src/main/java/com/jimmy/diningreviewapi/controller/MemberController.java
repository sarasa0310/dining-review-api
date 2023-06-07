package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.entity.Member;
import com.jimmy.diningreviewapi.dto.request.SignUpRequest;
import com.jimmy.diningreviewapi.dto.response.MemberResponse;
import com.jimmy.diningreviewapi.service.MemberService;
import com.jimmy.diningreviewapi.dto.request.MemberUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        Member member = signUpRequest.toEntity();

        Member savedMember = memberService.saveMember(member);

        MemberResponse response = MemberResponse.from(savedMember);

        return ResponseEntity.created(
                URI.create("/members/" + response.getId()))
                .body(response);
    }

    @GetMapping
    ResponseEntity<?> getProfile(@RequestParam String name) {
        Member foundMember = memberService.findMemberByName(name);

        MemberResponse response = MemberResponse.from(foundMember);

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    ResponseEntity<?> updateProfile(@RequestParam String name,
                                    @RequestBody MemberUpdate memberUpdate) {
        Member updatedMember = memberService.updateMember(name, memberUpdate);

        MemberResponse response = MemberResponse.from(updatedMember);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    ResponseEntity<?> leave(@RequestParam String name) {
        memberService.deleteMember(name);

        return ResponseEntity.noContent().build();
    }

}
