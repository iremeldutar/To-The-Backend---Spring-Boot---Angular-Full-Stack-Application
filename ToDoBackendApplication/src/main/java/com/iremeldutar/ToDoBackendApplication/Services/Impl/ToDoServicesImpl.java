package com.iremeldutar.ToDoBackendApplication.Services.Impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import com.iremeldutar.ToDoBackendApplication.Dto.TodosDto;
import com.iremeldutar.ToDoBackendApplication.Mapper.TodoMapper;
import com.iremeldutar.ToDoBackendApplication.Repository.ToDoRepository;
import com.iremeldutar.ToDoBackendApplication.Repository.UserRepository;
import com.iremeldutar.ToDoBackendApplication.Services.IToDoServices;
import com.iremeldutar.ToDoBackendApplication.entity.Todos;
import com.iremeldutar.ToDoBackendApplication.entity.User;

@Service
public class ToDoServicesImpl implements IToDoServices {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoServicesImpl(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TodosDto> KullaniciToDoList(Integer kullaniciId) {
        List<Todos> todosList = toDoRepository.findAllByUser_Id(kullaniciId);
        return todosList.stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodosDto> TamamlanmamisToDo(Integer kullaniciId) {
        List<Todos> todoList = toDoRepository.findByUser_IdAndCompletedFalse(kullaniciId);
        return todoList.stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodosDto saveToDo(int userId, TodosDto todosDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı: " + userId));


        Todos todos = TodoMapper.toEntity(todosDto);
        todos.setUser(user); 

        Todos saved = toDoRepository.save(todos);
        return TodoMapper.toDto(saved);
    }

	@Override
	public void deleteToDo(Integer todoId, Integer userId) {
		boolean exists = toDoRepository.existsById(todoId);
	    if (!exists) {
	        throw new IllegalArgumentException("Görev bulunamadı: " + todoId);
	    }

	    toDoRepository.deleteByIdAndUser_Id(todoId, userId);
		
	}

	@Override
	public TodosDto tamamlaToDo(Integer userId, Integer todoId) {
		var todo = toDoRepository.findByIdAndUser_Id(todoId, userId)
                .orElseThrow(() -> new NoSuchElementException("Görev bulunamadı"));

        if (!todo.isCompleted()) {                
            todo.setCompleted(true);
            
        }

        toDoRepository.save(todo);               
        return TodoMapper.toDto(todo);             
        }
	}

