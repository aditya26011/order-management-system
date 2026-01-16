package com.example.order_management_system.order.controller.dto;

import com.example.order_management_system.order.controller.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;

}
