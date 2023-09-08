package com.poliakova.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */

/**
 * The custom exception handler for fetching and identifying errors.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * All exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage("An unexpected error occurred");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Error 400: Bad Request.
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiError> handleBadRequestException(HttpClientErrorException e) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Error 404: Not Found.
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ApiError> handleNotFoundException(HttpClientErrorException e) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(HttpStatus.NOT_FOUND);
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Error 401: Unauthorized.
     */
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ApiError> handleUnauthorizedException(HttpClientErrorException e) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(HttpStatus.UNAUTHORIZED);
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Error 403: Forbidden.
     */
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ApiError> handleForbiddenException(HttpClientErrorException.Forbidden e) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(HttpStatus.FORBIDDEN);
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
