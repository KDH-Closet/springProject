package com.chunjae.kdhcloset.product.dto;

import com.chunjae.kdhcloset.product.entity.Product;
import com.chunjae.kdhcloset.product.entity.ProductImg;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    private Long product_idx;
    private String category;
    private String name;
    private String size;
    private int price;
    private int count;

    private List<MultipartFile> productFile;
    private List<String> originalFileName; // 원본 파일 이름
    private List<String> storedFileName; // 서버 저장용 파알 이름


    // Entity -> DTO로 변환
    public static ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_idx(product.getProductIdx());
        productDTO.setCategory(product.getCategory());
        productDTO.setName(product.getName());
        productDTO.setSize(product.getSize());
        productDTO.setPrice(product.getPrice());
        productDTO.setCount(product.getCount());

        List<String> originalFileNameList = new ArrayList<>();
        List<String> storedFileNameList = new ArrayList<>();
        for (ProductImg productImg : product.getProductImgs()) {
            originalFileNameList.add(productImg.getOriginalFileName());
            storedFileNameList.add(productImg.getStoredFileName());
        }
        productDTO.setOriginalFileName(originalFileNameList);
        productDTO.setStoredFileName(storedFileNameList);


        return productDTO;
    }

}
