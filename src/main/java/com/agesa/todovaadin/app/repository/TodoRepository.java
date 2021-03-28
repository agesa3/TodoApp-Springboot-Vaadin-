package com.agesa.todovaadin.app.repository;

import com.agesa.todovaadin.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
