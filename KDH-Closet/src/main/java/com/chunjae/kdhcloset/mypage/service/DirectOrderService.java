package com.chunjae.kdhcloset.mypage.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import com.chunjae.kdhcloset.mypage.dto.CartDTO;
import com.chunjae.kdhcloset.mypage.dto.DirectOrderDTO;
import com.chunjae.kdhcloset.mypage.domain.Order;
import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.mypage.repository.OrderRepository;
import com.chunjae.kdhcloset.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DirectOrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    @Autowired
    public DirectOrderService(OrderRepository orderRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public void oneOrder(CartDTO cartDto, String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저가 없습니다.")
        );
        //멤버 레포지에서 findById 멤버 엔티티를 생성후 그걸 주입
        Product product =  productRepository.findById(cartDto.getProductIdx()).orElseThrow(
                () -> new IllegalArgumentException()
        );

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderCount(cartDto.getCartCount());
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);

        member.setPoint((int) (member.getPoint() + product.getPrice() * 0.05 * cartDto.getCartCount()));
        memberRepository.save(member);
    }
}
