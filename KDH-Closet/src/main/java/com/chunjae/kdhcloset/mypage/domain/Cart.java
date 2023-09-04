package com.chunjae.kdhcloset.mypage.domain;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.mypage.dto.CartDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartIdx;

    private String cartSize;
    private int cartCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_idx")
    Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productIdx")
    private Product product;

    public static Cart fromCartDTO(Product product, CartDTO CartDTO) {
        Cart cart = new Cart();
        cart.setCartIdx(CartDTO.getCartIdx());
        cart.setCartSize(CartDTO.getCartSize());
        cart.setCartCount(CartDTO.getCartCount());
        cart.setProduct(product);
        return cart;
    }


}
