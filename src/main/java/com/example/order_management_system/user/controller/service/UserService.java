package com.example.order_management_system.user.controller.service;

import com.example.order_management_system.Exceptions.ResourceNotFoundException;
import com.example.order_management_system.user.controller.dto.UserDTO;
import com.example.order_management_system.user.controller.entity.User;
import com.example.order_management_system.user.controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


}
