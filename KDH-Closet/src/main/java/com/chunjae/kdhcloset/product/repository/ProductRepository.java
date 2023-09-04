package com.chunjae.kdhcloset.product.repository;

import com.chunjae.kdhcloset.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByAddDateDesc();
    List<Product> findProductsByNameContainingOrderByAddDateDesc(String name);
    List<Product> findAllByCategoryOrderByAddDateDesc(String category);
    List<Product> findProductsByCategoryAndNameContainingOrderByAddDateDesc(String category, String name);
}
