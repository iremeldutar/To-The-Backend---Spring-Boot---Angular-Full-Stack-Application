package com.iremeldutar.ToDoBackendApplication.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String token;
    private Integer userId;
    private String username;
    private String role;
    private String email;
    private LocalDateTime createdAt;
}
