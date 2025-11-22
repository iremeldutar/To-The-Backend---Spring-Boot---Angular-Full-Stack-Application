package com.iremeldutar.ToDoBackendApplication.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.iremeldutar.ToDoBackendApplication.entity.Todos;

import jakarta.transaction.Transactional;

@Repository
public interface ToDoRepository extends JpaRepository<Todos, Integer> {

    List<Todos> findAllByUser_Id(Integer userId);

    
    List<Todos> findByUser_IdAndCompletedFalse(Integer userId);

    Optional<Todos> findByIdAndUser_Id(Integer id, Integer userId);
    @Modifying
    @Transactional
    int deleteByIdAndUser_Id(Integer todoId, Integer userId);
}
