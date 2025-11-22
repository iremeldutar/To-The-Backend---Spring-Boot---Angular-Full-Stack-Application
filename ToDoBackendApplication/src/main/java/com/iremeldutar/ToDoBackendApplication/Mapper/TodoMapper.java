package com.iremeldutar.ToDoBackendApplication.Mapper;

import com.iremeldutar.ToDoBackendApplication.Dto.TodosDto;
import com.iremeldutar.ToDoBackendApplication.entity.Todos;

public class TodoMapper {

    public static TodosDto toDto(Todos todo) {
        TodosDto dto = new TodosDto();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.isCompleted());
        dto.setCreated(todo.getCreated());
        return dto;
    }

    public static Todos toEntity(TodosDto dto) {
        Todos todo = new Todos();
        todo.setId(dto.getId());
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());
        todo.setCreated(dto.getCreated());
        return todo;
    }
}
