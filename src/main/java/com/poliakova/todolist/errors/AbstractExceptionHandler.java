package com.poliakova.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

/**
 * @author oksanapoliakova on 08.09.2023
 * @projectName ToDoList
 */

/**
 * The abstract exception handler for fetching and identifying errors.
 * It handles exceptions in the application and returns corresponding HTTP responses with error information.
 */

@ControllerAdvice
public abstract class AbstractExceptionHandler {

    /**
     * Handle exceptions.
     * This method handles exceptions of type Exception that do not fall under more specific exception handlers.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    /**
     * Error 400: Bad Request.
     * This method handles exceptions, which can occur when receiving an invalid request.
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiError> handleBadRequestException(HttpClientErrorException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * Error 401: Unauthorized.
     * This method handles exceptions, which occur when the request is not authorized.
     */
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ApiError> handleUnauthorizedException(HttpClientErrorException e) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    /**
     * Error 403: Forbidden.
     * This method handles exceptions, which occur when access to the resource is forbidden.
     */
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ApiError> handleForbiddenException(HttpClientErrorException.Forbidden e) {
        return buildResponseEntity(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /**
     * Error 404: Not Found.
     * This method handles exceptions, which occur when the requested resource is not found
     */
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ApiError> handleNotFoundException(HttpClientErrorException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * Builds a ResponseEntity with the given HTTP status and error message.
     * @param status   The HTTP status to be set in the ResponseEntity.
     * @param message  The error message to be included in the ApiError.
     * @return         A ResponseEntity containing an ApiError with the specified status and message.
     */
    private ResponseEntity<ApiError> buildResponseEntity(HttpStatus status, String message) {
        ApiError error = new ApiError();
        error.setLocalDateTime(LocalDateTime.now());
        error.setHttpStatus(status);
        error.setMessage(message);

        return new ResponseEntity<>(error, status);
    }
}
