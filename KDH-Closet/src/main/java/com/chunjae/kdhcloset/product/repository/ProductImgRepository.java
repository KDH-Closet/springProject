package com.chunjae.kdhcloset.product.repository;

import com.chunjae.kdhcloset.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
}
