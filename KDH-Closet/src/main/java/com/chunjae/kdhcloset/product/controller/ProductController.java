package com.chunjae.kdhcloset.product.controller;

import com.chunjae.kdhcloset.mypage.dto.CartDTO;
import com.chunjae.kdhcloset.mypage.service.CartService;
import com.chunjae.kdhcloset.mypage.service.DirectOrderService;
import com.chunjae.kdhcloset.product.dto.ProductDTO;
import com.chunjae.kdhcloset.product.dto.ProductImgDTO;
import com.chunjae.kdhcloset.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final CartService cartService;
    private final DirectOrderService directOrderService;

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("productImgDTO", new ProductImgDTO());
        return "/shop/addProduct";
    }

    @PostMapping("/addProductpro")
    public String addProductWithFiles(@ModelAttribute("productDTO") ProductDTO productDTO, @ModelAttribute("productImgDTO") ProductImgDTO productImgDTO) throws IOException {

        productService.addProductWithFiles(productDTO, productImgDTO);

        return "redirect:/shop";
    }

    @GetMapping("/productDetail/{productIdx}")
    public String findById(@PathVariable Long productIdx, Model model) {

        ProductDTO productDTO = productService.findByidx(productIdx);
        model.addAttribute("product", productDTO);
        return "shop/productDetail";
    }

    /*여기 장바구니임*/
    @PostMapping("/details-options")
    public String addCart(@RequestParam(value = "productIdx", required = false) String productIdx,
                          @ModelAttribute("cartDTO") CartDTO cartDTO, @RequestParam("action") String action,
                          @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        System.out.println(action);
        if ("addCart".equals(action)) {
            Long productIdx1 = Long.valueOf(productIdx);
            cartService.addCart(productIdx1, cartDTO, userDetails.getUsername());
            return "redirect:/mypage/cartList";
        } else if ("directOrder".equals(action)) {
            directOrderService.oneOrder(cartDTO, userDetails.getUsername());
            return "redirect:/mypage/orderList";
        }
        return null;
    }

}
