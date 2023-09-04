package com.chunjae.kdhcloset.product.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImg_idx;
    private String originalFileName;
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productIdx")
     private Product product;


    // DTO -> Entity 로 변환
    public static ProductImg fromFile(Product product, String originalFileName, String storedFileName){
        ProductImg productImg = new ProductImg();
        productImg.setOriginalFileName(originalFileName);
        productImg.setStoredFileName(storedFileName);
        productImg.setProduct(product);

        return productImg;
    }
}
