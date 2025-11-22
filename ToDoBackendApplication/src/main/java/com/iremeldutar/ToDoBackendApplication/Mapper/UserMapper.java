package com.iremeldutar.ToDoBackendApplication.Mapper;

import com.iremeldutar.ToDoBackendApplication.Dto.UserDto;
import com.iremeldutar.ToDoBackendApplication.entity.User;

public final class UserMapper {

    private UserMapper() {}

    
    public static UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        
        return dto;
    }

    
    public static User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
       
        return user;
    }

    public static void updateEntity(User user, UserDto dto, boolean updatePassword) {
        if (user == null || dto == null) return;
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (updatePassword && dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword()); 
        }
    }
}
					