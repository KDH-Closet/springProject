package com.chunjae.kdhcloset.product.controller;

import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.dto.ProductImgDTO;
import com.chunjae.kdhcloset.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/addProduct")
    public String addProductForm(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("productImgDTO", new ProductImgDTO());
        return "addProduct";
    }

    @PostMapping("/addProductpro")
    public String addProductWithFiles(@ModelAttribute("productDTO") ProductDTO productDTO, @ModelAttribute("productImgDTO") ProductImgDTO productImgDTO) throws IOException {

        productService.addProductWithFiles(productDTO, productImgDTO);

        return "redirect:/shop/top";
    }
}
