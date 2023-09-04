package com.chunjae.kdhcloset.mypage.controller;

import com.chunjae.kdhcloset.member.service.MemberService;
import com.chunjae.kdhcloset.mypage.service.MypageService;
import com.chunjae.kdhcloset.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    private MypageService mypageService;
    private final MemberService memberService;
    private final ProductService productService;

    @Autowired
    public MypageController(MypageService orderService, MemberService memberService, ProductService productService) {
        this.mypageService = orderService;
        this.memberService = memberService;
        this.productService = productService;
    }

    //마이페이지 접근이 최근주문 리스트
    @GetMapping("/mypage/orderList")
    public String mypageRecentOrder(@AuthenticationPrincipal UserDetails userDetails, Model model) throws UsernameNotFoundException {
        mypageService.getRecentProductList(userDetails.getUsername(), model);
        return "/mypage/orderList";
    }
}
