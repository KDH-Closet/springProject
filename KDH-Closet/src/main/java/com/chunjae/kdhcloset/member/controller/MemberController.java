package com.chunjae.kdhcloset.member.controller;

import com.chunjae.kdhcloset.member.dto.MemberDto;
import com.chunjae.kdhcloset.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {
        return "/mypage/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberDto.JoinRequestDto requestDto) {
        memberService.join(requestDto);
        return "redirect:/";
    }

    @GetMapping("/edit-member-info")
    public String editMemberForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        memberService.editMemberInfoForm(model, userDetails.getUsername());
        return "/mypage/edit-member-info";
    }

    @PostMapping("/edit-member-info")
    public String editMember(MemberDto.EditMemberRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) throws UsernameNotFoundException {
        memberService.editMemberInfo(requestDto, userDetails.getUsername());
        return "/shop/top";
    }

    @PostMapping("/quit-member")
    public String quitMember(@AuthenticationPrincipal UserDetails userDetails) {
        memberService.quitMember(userDetails.getUsername());
        return "redirect:";
    }

}
