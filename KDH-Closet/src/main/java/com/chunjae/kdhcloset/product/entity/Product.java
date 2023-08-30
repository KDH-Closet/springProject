package com.chunjae.kdhcloset.product.entity;

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
    private LocalDateTime add_date;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImg> productImgs = new ArrayList<>();


    // DTO -> Entity 로 변환
    public static Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        product.setCount(productDTO.getCount());
        product.setAdd_date(LocalDateTime.now());
        return product;
    }


}

