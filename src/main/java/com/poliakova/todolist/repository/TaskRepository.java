package com.poliakova.todolist.repository;

import com.poliakova.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
