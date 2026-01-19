package com.example.order_management_system.inventory;

import com.example.order_management_system.Product.controller.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public void validateStock(Product product, Integer quantity) {
        if(product.getStock()<quantity){
            throw new RuntimeException(
                    "Insufficient stock for product: " + product.getProductName()
            );
        }
    }

    public void reduceStock(Product product, int quantity) {
        product.setStock(product.getStock() - quantity);
    }
}
