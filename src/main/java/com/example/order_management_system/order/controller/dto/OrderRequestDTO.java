package com.example.order_management_system.order.controller.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private List<OrderItemDTO> items;
}
