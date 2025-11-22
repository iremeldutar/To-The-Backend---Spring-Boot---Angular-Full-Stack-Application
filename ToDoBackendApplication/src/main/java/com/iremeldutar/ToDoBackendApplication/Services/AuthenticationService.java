package com.iremeldutar.ToDoBackendApplication.Services;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iremeldutar.ToDoBackendApplication.Dto.UserDto;
import com.iremeldutar.ToDoBackendApplication.Dto.UserRequest;
import com.iremeldutar.ToDoBackendApplication.Dto.UserResponse;
import com.iremeldutar.ToDoBackendApplication.Repository.UserRepository;
import com.iremeldutar.ToDoBackendApplication.entity.User;
import com.iremeldutar.ToDoBackendApplication.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtServices jwtServices;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

  
    public UserResponse save(UserDto userDto) {
       
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword())) 
                .email(userDto.getEmail())                               
                .role(Role.USER)
                .build();

       
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        user = userRepository.save(user); 

        String token = jwtServices.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

   
    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),
                userRequest.getPassword()
            )
        );

        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userRequest.getUsername());
        }

        String token = jwtServices.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
