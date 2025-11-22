package com.iremeldutar.ToDoBackendApplication.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	@NotBlank @Size(min = 3, max = 32)
	  private String username;

	  @NotBlank @Size(min = 6, max = 64)
	  private String password;

	 
	  private String email;
	
}
