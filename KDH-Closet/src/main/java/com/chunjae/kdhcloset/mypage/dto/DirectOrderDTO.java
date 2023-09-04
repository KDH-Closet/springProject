package com.chunjae.kdhcloset.mypage.dto;

import lombok.Data;

@Data
public class DirectOrderDTO {
    //상품상세보기 페이지에서 바로구매시 input name이랑 매칭될 필드들
    private Long orderIdx;

    private int orderCount;
    private int orderDate;

    private Long memberIdx;
    private Long productIdx;

}
