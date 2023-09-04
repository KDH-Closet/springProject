package com.chunjae.kdhcloset.product.service;

import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.domain.Product;
import com.chunjae.kdhcloset.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductListService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductDTO> findAll(String word){
        List<Product> productList = productRepository.findAllByOrderByAddDateDesc();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if( word == null ) {
            productList = productRepository.findAll(Sort.by(Sort.Order.desc("addDate")));
        } else {
            productList = productRepository.findProductsByNameContainingOrderByAddDateDesc(word);
        }

        for (Product product: productList){
            productDTOList.add(ProductDTO.fromProduct(product));
        }
        return productDTOList;
    }

    public List<ProductDTO> getProductByCateogory(String category, String word){
        List<ProductDTO> responseDto = new ArrayList<>();
        List<Product> products = null;

        if(word == null) {
            products = productRepository.findAllByCategoryOrderByAddDateDesc(category);
        } else {
            products = productRepository.findProductsByCategoryAndNameContainingOrderByAddDateDesc(category, word);
        }

        for(Product product : products) {
            responseDto.add(ProductDTO.fromProduct(product));
        }
        return responseDto;
    }
}
