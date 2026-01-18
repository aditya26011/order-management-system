package com.example.order_management_system.Product.controller;

import com.example.order_management_system.Product.controller.dto.ProductDTO;
import com.example.order_management_system.Product.controller.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> allProducts=productService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id){
       ProductDTO productDTO= productService.getProductByid(id);
       return new ResponseEntity<>(productDTO,HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        ProductDTO patchedProduct=productService.patchProduct(id,productDTO);
        return new ResponseEntity<>(patchedProduct,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
       if (productService.deleteById(id)) {
           return new ResponseEntity<>(true, HttpStatus.OK);
       }
       else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
