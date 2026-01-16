package com.example.order_management_system.Product.controller.service;

import com.example.order_management_system.Product.controller.dto.ProductDTO;
import com.example.order_management_system.Product.controller.entity.Product;
import com.example.order_management_system.Product.controller.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
   private final ModelMapper modelMapper;

    public ProductDTO addProduct(ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        Product savedProduct = productRepository.save(product);

        ProductDTO response = modelMapper.map(savedProduct, ProductDTO.class);


        return response;
    }


    public List<ProductDTO> getAllProducts() {
        List<Product> productList=productRepository.findAll();
       return productList
               .stream()
               .map((element) -> modelMapper.map(element, ProductDTO.class))
                       .collect(Collectors.toList());
    }

    public ProductDTO patchProduct(Long id,ProductDTO productDTO) {
      Product product=  productRepository.findById(id).orElseThrow(()->new NoSuchElementException("Product with id not found:"+ id));

    if(productDTO.getProductName()!=null){
        product.setProductName(productDTO.getProductName());
    }
    if(productDTO.getStock()!=null){
        product.setStock(productDTO.getStock());
    }
    if(productDTO.getPrice()!=null){
        product.setPrice(productDTO.getPrice());
    }
    if(productDTO.getActive()!=null){
        product.setActive(productDTO.getActive());
    }
    Product saved=productRepository.save(product);
    ProductDTO response =modelMapper.map(saved,ProductDTO.class);
    return  response;
    }
}
