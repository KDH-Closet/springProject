package com.chunjae.kdhcloset.mypage.controller;

import com.chunjae.kdhcloset.mypage.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class CartController {

    private final CartService cartService;

    @GetMapping("/cartList")
    public String viewCartList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        cartService.getCartItemsByMemberIdx(model, userDetails.getUsername());
        return "/mypage/cartList";
    }

    @PostMapping("/cartListDelete")
    public String deleteCartList(@RequestParam(value = "cartIdx") Long cartIdx) {
        System.out.println("cartListDelete 페이지 넘어옴" + cartIdx);
        cartService.deleteCartItem(cartIdx);
        return "redirect:/mypage/cartList";
    }

    @PostMapping("/cart-order")
    public String goCart(@RequestParam String[] cartIdx, @AuthenticationPrincipal UserDetails userDetails){
        cartService.cartToOrder(cartIdx, userDetails.getUsername());
        return "redirect:/mypage/orderList";
    }
}
