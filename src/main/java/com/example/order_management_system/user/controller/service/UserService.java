package com.example.order_management_system.user.controller.service;

import com.example.order_management_system.Exceptions.ResourceNotFoundException;
import com.example.order_management_system.auth.dto.SignUpDto;
import com.example.order_management_system.auth.dto.UserDto;
import com.example.order_management_system.user.controller.dto.UserDTO;
import com.example.order_management_system.user.controller.entity.User;
import com.example.order_management_system.user.controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->  new UsernameNotFoundException("User with email not found:"+username));
    }
    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with this email already present:"+ signUpDto.getEmail());
        }
        User user1=new User();
        user1.setEmail(signUpDto.getEmail());
        user1.setPassword(passwordEncoder.encode(signUpDto.getPassword())); //encoding the pass IMP

        User savedUser= userRepository.save(user1);

        UserDto userDto=new UserDto();
        userDto.setEmail(savedUser.getEmail());
        userDto.setId(savedUser.getId());

        return userDto;
    }
}
