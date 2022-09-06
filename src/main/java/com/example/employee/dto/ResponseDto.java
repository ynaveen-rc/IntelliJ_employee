package com.example.employee.dto;

import java.util.Date;
import java.util.Objects;

public class ResponseDto<T> {
    private Date timeStamp;
    private Integer httpStatus;
    private T data;
    private ErrorDto error;

    public ResponseDto() {
    }

    public ResponseDto(T data, Integer httpStatus) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.timeStamp = new Date();
    }

    public ResponseDto(T data, Integer httpStatus, ErrorDto error) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.timeStamp = new Date();
        this.error = error;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public T getData() {
        return data;
    }

    public ErrorDto getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto<?> that = (ResponseDto<?>) o;
        return Objects.equals(httpStatus, that.httpStatus) && Objects.equals(data, that.data) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, data, error);
    }
}
