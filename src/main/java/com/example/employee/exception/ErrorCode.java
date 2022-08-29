package com.example.employee.exception;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("Employee that is requested for is not found"),
    USER_ALREADY_EXISTS("User already exists"),
    USER_NOT_VALID("User is not valid");
    private String message;

    public String getMessage() {
        return message;
    }

    ErrorCode(String message) {
        this.message = message;
    }

}
