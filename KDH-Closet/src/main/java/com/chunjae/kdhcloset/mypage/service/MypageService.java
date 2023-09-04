
package com.chunjae.kdhcloset.mypage.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import com.chunjae.kdhcloset.mypage.domain.Order;
import com.chunjae.kdhcloset.mypage.dto.MypageOrderDTO;
import com.chunjae.kdhcloset.mypage.repository.OrderRepository;
import com.chunjae.kdhcloset.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class MypageService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private MemberRepository memberRepository;
    @Autowired
    public MypageService(OrderRepository orderRepository, ProductRepository productRepository, MemberRepository memberRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    public boolean existsById(Long userDetailName) {
        return memberRepository.existsById(userDetailName);
    }
    //member_id 가지고 List<Order>를 가지고온다 Order 엔티티의 product_idx를 가지고 상품과 이미지를 가지고온다

    public void getRecentProductList(String memberId, Model model) throws UsernameNotFoundException {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저가 없습니다.")
        );
        List<Order> recentOrder = orderRepository.findAllWithProductAndMember(member.getMemberIdx());
        List<MypageOrderDTO> mypageDto = new ArrayList<>();

        for(Order order : recentOrder) {
            mypageDto.add(MypageOrderDTO.fromOrder(order));
        }

        model.addAttribute("orderList", mypageDto);
    }
}
