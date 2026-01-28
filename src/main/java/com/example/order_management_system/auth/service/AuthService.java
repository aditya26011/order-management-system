package com.example.order_management_system.auth.service;

import com.example.order_management_system.auth.dto.LoginDto;
import com.example.order_management_system.auth.dto.LoginResponseDto;
import com.example.order_management_system.auth.dto.SignUpDto;
import com.example.order_management_system.auth.dto.UserDto;
import com.example.order_management_system.auth.jwt.JwtService;
import com.example.order_management_system.user.controller.entity.User;
import com.example.order_management_system.user.controller.repository.UserRepository;
import com.example.order_management_system.user.controller.service.UserService;
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
        private final UserService userService;

    public LoginResponseDto login(LoginDto loginDto) {
      Authentication authenticated= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

      User user= (User) authenticated.getPrincipal();
        String accessToken= jwtService.generateAccessToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);

        return new LoginResponseDto(user.getId(),accessToken,refreshToken);

    }

    public LoginResponseDto refreshToken(String refreshToken) {
        //validate if the refreshtoken is valid
        Long userId= jwtService.getUserIdFromToken(refreshToken);
        User user=userService.getUserById(userId);

        String accessToken= jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(),accessToken,refreshToken);


    }
}
