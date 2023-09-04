package com.chunjae.kdhcloset.product.controller;

import com.chunjae.kdhcloset.mypage.dto.DirectOrderDTO;
import com.chunjae.kdhcloset.mypage.service.DirectOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectOrderController {
    private DirectOrderService directOrderService;
    @Autowired
    public DirectOrderController(DirectOrderService directOrderService) {
        this.directOrderService = directOrderService;
    }

    // PostMapping으로 바꿔야함
    //상품 하나의 정보 멤버 정보 오더 테이블에 인서트 후 마이페이지로
    /*@GetMapping("one_order")
    public String oneOrder(DirectOrderDTO directOrderDTO) {
        directOrderDTO.setMemberIdx(1L);  //임시 userDetails Name으로 아이디 셋팅
        directOrderDTO.setProductIdx(7L);
        directOrderDTO.setOrderCount(5);

        directOrderService.oneOrder(directOrderDTO);
        return "redirect:/mypage";    //redirect 하기 controller로
    }*/
}
