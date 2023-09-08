package com.poliakova.todolist.service;

import com.poliakova.todolist.model.Task;
import com.poliakova.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */

@Service
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> optionalExistingTask = taskRepository.findById(id);
        if (optionalExistingTask.isPresent()) {
            Task existingTask = optionalExistingTask.get();
            existingTask.setName(updatedTask.getName());
            existingTask.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existingTask);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
