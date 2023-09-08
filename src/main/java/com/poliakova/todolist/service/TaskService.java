package com.poliakova.todolist.service;

import com.poliakova.todolist.model.Task;

import java.util.List;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */
public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}
