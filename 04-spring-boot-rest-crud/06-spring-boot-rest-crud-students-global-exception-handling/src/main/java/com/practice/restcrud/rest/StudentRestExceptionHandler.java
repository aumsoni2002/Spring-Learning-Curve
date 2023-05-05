package com.practice.restcrud.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// With the help of '@ControllerAdvice' annotation, it makes the below exception handling code available on a global basis,
// for a given application.
@ControllerAdvice
public class StudentRestExceptionHandler {
    // Here we will put Exception Handling Code, so that we can make use of them globally that is across whole application.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
