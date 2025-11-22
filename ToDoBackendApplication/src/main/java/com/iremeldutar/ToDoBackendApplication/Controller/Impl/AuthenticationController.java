package com.iremeldutar.ToDoBackendApplication.Controller.Impl;

import com.iremeldutar.ToDoBackendApplication.Dto.UserDto;
import com.iremeldutar.ToDoBackendApplication.Dto.UserRequest;
import com.iremeldutar.ToDoBackendApplication.Dto.UserResponse;
import com.iremeldutar.ToDoBackendApplication.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin 
public class AuthenticationController {

    private final AuthenticationService authenticationService;

   
    @PostMapping({"/register", "/save"})
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

   
    @PostMapping({"/login", "/auth"})
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}
