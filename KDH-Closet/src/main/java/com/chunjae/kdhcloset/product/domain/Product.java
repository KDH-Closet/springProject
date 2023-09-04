package com.chunjae.kdhcloset.product.domain;

import com.chunjae.kdhcloset.mypage.domain.Cart;
import com.chunjae.kdhcloset.product.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productIdx;

    private String category;
    private String name;
    private String size;
    private int price;
    private int count;
    private LocalDateTime addDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductImg> productImgs = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cart> cart = new ArrayList<>();



    // DTO -> Entity 로 변환
    public static Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductIdx(productDTO.getProductIdx());
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        product.setCount(productDTO.getCount());
        product.setAddDate(LocalDateTime.now());
        return product;
    }


}

