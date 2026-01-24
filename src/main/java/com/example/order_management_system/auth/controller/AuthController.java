package com.example.order_management_system.auth.controller;

import com.example.order_management_system.auth.dto.LoginDto;
import com.example.order_management_system.auth.dto.SignUpDto;
import com.example.order_management_system.auth.dto.UserDto;
import com.example.order_management_system.auth.service.AuthService;
import com.example.order_management_system.user.controller.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
        public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        UserDto userDto=userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto,HttpServletRequest request ,HttpServletResponse response){
        String token=authService.login(loginDto);
        Cookie cookie=new Cookie("token",token);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
