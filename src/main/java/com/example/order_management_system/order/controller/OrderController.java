package com.example.order_management_system.order.controller;

import com.example.order_management_system.order.controller.dto.OrderRequestDTO;
import com.example.order_management_system.order.controller.dto.OrderResponseDTO;
import com.example.order_management_system.order.controller.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/my")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(){
        List<OrderResponseDTO> orders=orderService.getMyOrders();
        return new ResponseEntity<>(orders,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("id") Long Id){
        OrderResponseDTO orderById=orderService.getOrderById(Id);
        return new ResponseEntity<>(orderById,HttpStatus.OK);
    }
}
