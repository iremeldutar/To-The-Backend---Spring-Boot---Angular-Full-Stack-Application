package com.iremeldutar.ToDoBackendApplication.Controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iremeldutar.ToDoBackendApplication.Controller.ITodoDoContoller;
import com.iremeldutar.ToDoBackendApplication.Dto.TodosDto;
import com.iremeldutar.ToDoBackendApplication.Services.IToDoServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "*")
public class ToDoController implements ITodoDoContoller {

    @Autowired
    private IToDoServices iToDoServices;

    
    @GetMapping("/gorevlerim/{id}")
    @Override
    public ResponseEntity<List<TodosDto>> KullaniciToDoList(@PathVariable("id") Integer kullaniciId) {
        return ResponseEntity.ok(iToDoServices.KullaniciToDoList(kullaniciId));
    }

    
    @GetMapping("/tamamlanmamis/{id}")
    @Override
    public ResponseEntity<List<TodosDto>> TamamlanmamisToDo(@PathVariable("id") Integer kullaniciId) {
        return ResponseEntity.ok(iToDoServices.TamamlanmamisToDo(kullaniciId));
    }

   
    @PostMapping("/save/{userId}") 
    @Override
    public ResponseEntity<TodosDto> saveToDo(@PathVariable("userId") Integer userId,
                                             @RequestBody TodosDto dto) {
        TodosDto saved = iToDoServices.saveToDo(userId, dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{userId}/{todoId}")
    @Override
    public ResponseEntity<Void> deleteToDo(@PathVariable("userId") Integer userId,
                                           @PathVariable("todoId") Integer todoId) {
        iToDoServices.deleteToDo(todoId, userId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/tamamla/{userId}/{todoId}")
    @Override
    public ResponseEntity<TodosDto> tamamlaToDo(
            @PathVariable("userId") Integer userId,
            @PathVariable("todoId") Integer todoId) {

        TodosDto dto = iToDoServices.tamamlaToDo(userId, todoId);
        return ResponseEntity.ok(dto);
    }
}

