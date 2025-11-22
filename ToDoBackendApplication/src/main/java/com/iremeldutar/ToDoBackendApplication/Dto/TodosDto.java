package com.iremeldutar.ToDoBackendApplication.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodosDto {
		private int id;
		private String description;
	    private String title;
	    private boolean completed;
	    private LocalDateTime created;

}
