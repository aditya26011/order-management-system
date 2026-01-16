package com.example.order_management_system.user.controller;

import com.example.order_management_system.user.controller.dto.UserDTO;
import com.example.order_management_system.user.controller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
