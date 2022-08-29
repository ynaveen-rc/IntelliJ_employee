package com.example.employee.exception;

import com.example.employee.dto.ErrorDto;
import com.example.employee.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppGeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto handleAppGeneralException(AppGeneralException exception) {
        return new ResponseDto(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), new ErrorDto(ErrorCode.valueOf(exception.getMessage())));
    }
}
