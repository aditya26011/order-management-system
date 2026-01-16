package com.example.order_management_system.user.controller.dto;

import com.example.order_management_system.order.controller.entity.Order;
import com.example.order_management_system.user.controller.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String City;
    private Role role;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Order> orders;


}
