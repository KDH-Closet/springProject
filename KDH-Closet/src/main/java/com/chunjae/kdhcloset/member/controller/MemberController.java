package com.chunjae.kdhcloset.member.controller;

import ch.qos.logback.core.model.Model;
import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.dto.MemberDto;
import com.chunjae.kdhcloset.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid MemberDto.JoinRequestDto requestDto) {
        return memberService.join(requestDto);
    }

/*    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto.JoinRequestDto requestDto) {
        memberService.join(requestDto);
        return "index";
    }*/
}
