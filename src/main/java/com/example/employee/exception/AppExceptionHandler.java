package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("errorMessage", exception.getMessage());
        return new ResponseEntity<>(hashMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
