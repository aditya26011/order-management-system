package com.example.order_management_system.Product.controller.service;

import com.example.order_management_system.Product.controller.dto.ProductDTO;
import com.example.order_management_system.Product.controller.entity.Product;
import com.example.order_management_system.Product.controller.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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


}
