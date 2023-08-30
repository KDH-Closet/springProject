package com.chunjae.kdhcloset.product.service;

import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.dto.ProductImgDTO;
import com.chunjae.kdhcloset.product.entity.Product;
import com.chunjae.kdhcloset.product.entity.ProductImg;
import com.chunjae.kdhcloset.product.repository.ProductImgRepository;
import com.chunjae.kdhcloset.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private ProductRepository productRepository;

    private ProductImgRepository productImgRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductImgRepository productImgRepository) {
        this.productRepository = productRepository;
        this.productImgRepository = productImgRepository;
    }

    public void addProductWithFiles(ProductDTO productDTO, ProductImgDTO productImgDTO) throws IOException {
        Product product = Product.fromDTO(productDTO);
        productRepository.save(product);

        Long product_idx = productRepository.save(product).getProductIdx();
        Product product1 =  productRepository.findById(product_idx).get();
        for (MultipartFile productFile : productDTO.getProductFile()) {

            String originalFilename = productFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String relativePath = "/Users/kim/images/" + storedFileName;
            productFile.transferTo(new File(relativePath));

            ProductImg productImg = ProductImg.fromFile(product1, originalFilename, storedFileName);
            productImgRepository.save(productImg);
        }
    }

    @Transactional
    public List<ProductDTO> findAll(){
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: productList){
            productDTOList.add(ProductDTO.fromProduct(product));
        }
        return productDTOList;
    }

    @Transactional
    public ProductDTO findByidx(Long product_idx){
        Optional<Product> optionalProduct = productRepository.findById(product_idx);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            ProductDTO productDTO = ProductDTO.fromProduct(product);
            return productDTO;
        } else {
            return null;
        }
    }
}
