package com.chunjae.kdhcloset.mypage.dto;

import com.chunjae.kdhcloset.mypage.domain.Cart;

import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.product.domain.ProductImg;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {
    
    private Long cartIdx;

    private String cartSize;
    private int cartCount;
    private Long productIdx;
    private String name;
    private int price;
    private Long memberIdx;

    private List<MultipartFile> productFile;
    private List<String> originalFileName; // 원본 파일 이름
    private List<String> storedFileName; // 서버 저장용 파알 이름

    public static CartDTO fromCart(Cart cart,Product product){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartIdx(cart.getCartIdx());
        cartDTO.setCartSize(cart.getCartSize());
        cartDTO.setCartCount(cart.getCartCount());
        cartDTO.setProductIdx(cart.getProduct().getProductIdx());
        cartDTO.setName(cart.getProduct().getName());
        cartDTO.setPrice(cart.getProduct().getPrice());

        List<String> originalFileNameList = new ArrayList<>();
        List<String> storedFileNameList = new ArrayList<>();
        for (ProductImg productImg : product.getProductImgs()) {
            originalFileNameList.add(productImg.getOriginalFileName());
            storedFileNameList.add(productImg.getStoredFileName());
        }
        cartDTO.setOriginalFileName(originalFileNameList);
        cartDTO.setStoredFileName(storedFileNameList);

        return cartDTO;
    }
}
