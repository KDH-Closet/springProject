package com.chunjae.kdhcloset.mypage.dto;

import com.chunjae.kdhcloset.member.domain.Member;
import com.chunjae.kdhcloset.mypage.domain.Order;
import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.product.domain.ProductImg;
import com.chunjae.kdhcloset.product.dto.MemberDTO;
import com.chunjae.kdhcloset.product.dto.ProductDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MypageOrderDTO {
    private Long orderIdx;
    private int orderCount;
    private LocalDateTime orderDate;
    private Long memberIdx;
    private Long productIdx;

    private MemberDTO memberDTO;
    private ProductDTO productDTO;

    public static MypageOrderDTO fromOrder(Order order) {
        MypageOrderDTO dto = new MypageOrderDTO();
        dto.setOrderIdx(order.getOrderIdx());
        dto.setOrderCount(order.getOrderCount());
        dto.setOrderDate(order.getOrderDate());
        dto.setMemberDTO(fromMember(order.getMember()));
        dto.setProductDTO(fromProduct(order.getProduct()));

        return dto;
    }


    private static MemberDTO fromMember(Member m) {
        MemberDTO dto = new MemberDTO();
        dto.setMemberIdx(m.getMemberIdx());
        dto.setName(m.getName());
        dto.setGender(String.valueOf(m.getGender()));
        dto.setId(m.getId());
        dto.setBirth(m.getBirth());
        dto.setTel(m.getTel());
        dto.setZipcode(m.getZipCode());
        dto.setAddr(m.getAddr());
        dto.setPoint(m.getPoint());
        dto.setRegDate(m.getCreatedDate());
        dto.setModifyDate(m.getModifiedDate());

        return dto;
    }

    private static ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdx(product.getProductIdx());
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
