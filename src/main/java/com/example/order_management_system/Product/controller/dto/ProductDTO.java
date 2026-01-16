package com.example.order_management_system.Product.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private Double price;
    private Integer stock;
    private Boolean active;


}
