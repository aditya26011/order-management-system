package com.example.order_management_system.auth.service;

import com.example.order_management_system.auth.dto.LoginDto;
import com.example.order_management_system.auth.dto.SignUpDto;
import com.example.order_management_system.auth.dto.UserDto;
import com.example.order_management_system.auth.jwt.JwtService;
import com.example.order_management_system.user.controller.entity.User;
import com.example.order_management_system.user.controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;

    public String login(LoginDto loginDto) {
      Authentication authenticated= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

      User user= (User) authenticated.getPrincipal();
        return jwtService.generateToken(user);

    }
}
