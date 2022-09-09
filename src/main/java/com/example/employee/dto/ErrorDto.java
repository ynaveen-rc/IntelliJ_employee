package com.example.employee.dto;

import com.example.employee.exception.ErrorCode;
import org.springframework.stereotype.Component;

//@Component
public class ErrorDto {
    private String code;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(ErrorCode errorCode) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
