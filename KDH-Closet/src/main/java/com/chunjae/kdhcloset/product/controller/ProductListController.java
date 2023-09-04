package com.chunjae.kdhcloset.product.controller;

import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductListController {

    @Autowired
    private ProductListService productListService;

    @GetMapping("/shop")
    public String getProductAndImg(Model model, @RequestParam(required = false) String word) {

        List<ProductDTO> products =  productListService.findAll(word);
        model.addAttribute("products", products);

        return "/shop/shop";
    }

    //category 가 top pants outer acc

    @GetMapping("/shop/{category}")
    public String getProductByCategory(@PathVariable String category,
                                       @RequestParam(required = false) String word, Model model){
        System.out.println("category start, 카테고리,word : " + category + ", " + word);

        List<ProductDTO> products = productListService.getProductByCateogory(category, word);
        model.addAttribute("products",products);

        return "/shop/shop";
    }
}
