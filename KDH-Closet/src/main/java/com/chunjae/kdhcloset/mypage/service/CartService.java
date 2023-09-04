package com.chunjae.kdhcloset.mypage.service;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.member.repository.MemberRepository;
import com.chunjae.kdhcloset.mypage.domain.Cart;
import com.chunjae.kdhcloset.mypage.dto.CartDTO;
import com.chunjae.kdhcloset.mypage.domain.Order;
import com.chunjae.kdhcloset.mypage.repository.CartRepository;
import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.mypage.repository.OrderRepository;
import com.chunjae.kdhcloset.product.repository.ProductRepository;
import com.chunjae.kdhcloset.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public void addCart(Long productIdx1, CartDTO cartDTO, String memberId) {
        System.out.println("장바구니 서비스 들어옴");
        Product product = productRepository.findById(productIdx1).orElseThrow(
                () -> new IllegalArgumentException()
        );

        Cart cart = Cart.fromCartDTO(product, cartDTO);
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저를 찾을 수 없습니다.")
        );
        cart.setMember(member);
        cartRepository.save(cart);
    }

    @Transactional
    public void getCartItemsByMemberIdx(Model model, String memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저가 없습니다.")
        );
        List<Cart> cartItems = cartRepository.findByMember(member);
        List<CartDTO> cartDTOs = new ArrayList<>(); 

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct();
            cartDTOs.add(CartDTO.fromCart(cartItem, product));
        }

        model.addAttribute("cartItems", cartDTOs);
    }

    @Transactional
    public void deleteCartItem(Long cartIdx) {
        cartRepository.deleteByCartIdx(cartIdx);
    }

    public void cartToOrder(String[] cartIdx, String memberId) {
        List<Order> orders = new ArrayList<>();
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UsernameNotFoundException("해당 아이디를 가진 유저를 찾을 수 없습니다.")
        );

        int point = 0;
        for (String cart : cartIdx) {
            Order order = new Order();
            // 장바구니에 담긴 idx로 orderIdx = null엔티티 가져오기
            Cart cartEntity = cartRepository.findById(Long.parseLong(cart)).orElseThrow(
                    () -> new IllegalArgumentException("찾을 수 없음")
            );

            order.setMember(cartEntity.getMember());
            order.setProduct(cartEntity.getProduct());
            order.setOrderDate(LocalDateTime.now());
            order.setOrderCount(cartEntity.getCartCount());

            orders.add(order);

            // DTO cart idx로 cart table에서 삭제
            cartRepository.deleteById(cartEntity.getCartIdx());

            point += (int) (cartEntity.getProduct().getPrice() * 0.05 *  cartEntity.getCartCount());
        }

        // 한 번만 저장
        orderRepository.saveAll(orders);

        member.setPoint(member.getPoint() + point);
        memberRepository.save(member);
    }

//    public void cartToOrder(long[] cartIdx) {
//        Order order = null;
//        Cart cartEntity = null;
//
//        for(long cart : cartIdx) {
//            order = new Order();
//            //장바구니 담긴 idx로 엔티디 가져오기
//            cartEntity = cartRepository.findById(cart).get();
//
//            order.setMember( cartEntity.getMember() );
//            order.setProduct( cartEntity.getProduct() );
//            order.setOrderDate( LocalDateTime.now() );
//            order.setOrderCount( cartEntity.getCart_count() );
//
//            // cart dto -> entity -> order table로 복사 저장?
//            orderRepository.save(order);
//
//            //dto cart idx로 cart table에서 삭제
//            cartRepository.deleteById(cartEntity.getCart_idx());
//
//        }
//    }
}




