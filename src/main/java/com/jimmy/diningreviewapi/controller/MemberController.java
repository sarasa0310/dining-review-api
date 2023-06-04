package com.jimmy.diningreviewapi.controller;

import com.jimmy.diningreviewapi.domain.Member;
import com.jimmy.diningreviewapi.dto.MemberPostDto;
import com.jimmy.diningreviewapi.service.MemberService;
import com.jimmy.diningreviewapi.dto.MemberUpdateDto;
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
    ResponseEntity<?> signUp(@RequestBody @Valid MemberPostDto memberPostDto) {
        Member member = memberPostDto.toEntity();

        Member savedMember = memberService.saveMember(member);

        return ResponseEntity.created(
                URI.create("/members/" + savedMember.getId())).build();
    }

    @GetMapping
    ResponseEntity<?> getProfile(@RequestParam String name) {
        Member foundMember = memberService.findMember(name);

        MemberPostDto response = MemberPostDto.from(foundMember);

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    ResponseEntity<?> updateProfile(@RequestParam String name,
                                    @RequestBody MemberUpdateDto memberUpdateDto) {
        Member updatedMember = memberService.updateMember(name, memberUpdateDto);

        MemberPostDto response = MemberPostDto.from(updatedMember);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    ResponseEntity<?> leave(@RequestParam String name) {
        memberService.deleteMember(name);

        return ResponseEntity.noContent().build();
    }

}
