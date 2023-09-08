package com.poliakova.todolist.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */

/**
 * Represents error information to be included in responses when errors occur.
 * This class is used for structured error reporting in the application.
 */

@Data
public class ApiError {
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;
}
