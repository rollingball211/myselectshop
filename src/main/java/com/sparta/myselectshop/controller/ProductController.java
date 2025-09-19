package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
           //페이징 처리
           @RequestParam("page") int page,
           @RequestParam("size") int size,
           @RequestParam("sortBy") String sortBy,
           @RequestParam("isAsc") boolean isAsc,
           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return productService.getProducts(userDetails.getUser(),page-1,size,sortBy,isAsc);
        //page는 1이지만 서버에선 0부터 시작하니까

    }

//    @GetMapping("/admin/products")
//    public List<ProductResponseDto> getAllProducts() {
//        return productService.getAllProducts();
//    }


}
