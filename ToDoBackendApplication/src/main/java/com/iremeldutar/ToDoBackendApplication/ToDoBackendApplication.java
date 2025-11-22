package com.iremeldutar.ToDoBackendApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EntityScan(basePackages = {"com.iremeldutar.ToDoBackendApplication.entity"})
@EnableJpaRepositories(basePackages = {"com.iremeldutar.ToDoBackendApplication.Repository"})
public class ToDoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoBackendApplication.class, args);
    }
}
