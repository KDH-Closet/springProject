package com.chunjae.kdhcloset.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProductImgDTO {
    private MultipartFile productFile;

    private String originalFileName; // 원본 파일 이름
    private String storeFileName; // 서버 저장용 파이 이름
    private int fileAttached; // 파일 첨부 여부 (첨부 1, 미첨부 0)
}
