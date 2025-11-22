package com.iremeldutar.ToDoBackendApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iremeldutar.ToDoBackendApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
	  boolean existsByEmail(String email);
	
}
