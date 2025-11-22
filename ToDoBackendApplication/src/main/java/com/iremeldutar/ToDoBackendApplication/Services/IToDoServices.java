package com.iremeldutar.ToDoBackendApplication.Services;

import java.util.List;

import com.iremeldutar.ToDoBackendApplication.Dto.TodosDto;
import com.iremeldutar.ToDoBackendApplication.entity.Todos;

public interface IToDoServices {
	List<TodosDto> KullaniciToDoList(Integer kullaniciId);
	List<TodosDto> TamamlanmamisToDo(Integer kullaniciId);
	public TodosDto saveToDo(int userId, TodosDto todosDto);
	void deleteToDo(Integer todoId, Integer userId);
	TodosDto tamamlaToDo(Integer userId, Integer todoId);

}
