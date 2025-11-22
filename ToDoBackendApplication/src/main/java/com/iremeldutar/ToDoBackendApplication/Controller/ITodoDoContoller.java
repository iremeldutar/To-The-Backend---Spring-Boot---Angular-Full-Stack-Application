package com.iremeldutar.ToDoBackendApplication.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.iremeldutar.ToDoBackendApplication.Dto.TodosDto;
import com.iremeldutar.ToDoBackendApplication.entity.Todos;

public interface ITodoDoContoller {
	
	
	ResponseEntity<List<TodosDto>> KullaniciToDoList(Integer kullaniciId);
	ResponseEntity<List<TodosDto>>TamamlanmamisToDo(Integer kullaniciId);
	ResponseEntity<TodosDto> saveToDo(Integer userId, TodosDto todosDto);
    ResponseEntity<Void>   deleteToDo(Integer todoId, Integer userId);
    ResponseEntity<TodosDto> tamamlaToDo(Integer userId, Integer todoId);

}
