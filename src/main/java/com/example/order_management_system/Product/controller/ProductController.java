package com.example.order_management_system.Product.controller;

import com.example.order_management_system.Product.controller.dto.ProductDTO;
import com.example.order_management_system.Product.controller.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
  public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        System.out.println("Product dto,"+ productDTO);
        ProductDTO addedProduct=productService.addProduct(productDTO);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }
}
