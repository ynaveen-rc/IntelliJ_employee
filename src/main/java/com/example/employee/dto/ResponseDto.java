package com.example.employee.dto;

import java.util.Date;
import java.util.Objects;

public class ResponseDto<T> {
    private Date timeStamp;
    private Integer httpStatus;
    private T results;
    private ErrorDto error;

//    public ResponseDto() {
//    }

    public ResponseDto(T results, Integer httpStatus) {
        this.results = results;
        this.httpStatus = httpStatus;
        this.timeStamp = new Date();
    }

    public ResponseDto(T results, Integer httpStatus, ErrorDto error) {
        this.results = results;
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

    public T getResults() {
        return results;
    }

    public ErrorDto getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto<?> that = (ResponseDto<?>) o;
        return Objects.equals(httpStatus, that.httpStatus) && Objects.equals(results, that.results) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, results, error);
    }
}
