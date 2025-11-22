package com.iremeldutar.ToDoBackendApplication.Controller.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	@GetMapping
	public ResponseEntity<String> helloworld(){
		return ResponseEntity.ok("welcome dashboard");
	}

}
