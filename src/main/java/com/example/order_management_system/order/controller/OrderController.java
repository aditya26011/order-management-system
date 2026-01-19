package com.example.order_management_system.order.controller;

import com.example.order_management_system.order.controller.dto.OrderRequestDTO;
import com.example.order_management_system.order.controller.dto.OrderResponseDTO;
import com.example.order_management_system.order.controller.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    //Place order
    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderedProduct=orderService.placeOrder(orderRequestDTO);
        return new ResponseEntity<>(orderedProduct, HttpStatus.CREATED);
    }
}
