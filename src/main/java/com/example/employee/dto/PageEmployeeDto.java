package com.example.employee.dto;

import com.example.employee.model.Employee;

import java.util.List;
import java.util.Objects;

public class PageEmployeeDto {
    private List<Employee> content;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private int pageSize;

    public PageEmployeeDto(List<Employee> content, int pageNumber, int totalPages, long totalElements, int pageSize) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
    }

    public List<Employee> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageEmployeeDto that = (PageEmployeeDto) o;
        return pageNumber == that.pageNumber && totalPages == that.totalPages && totalElements == that.totalElements && pageSize == that.pageSize && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageNumber, totalPages, totalElements, pageSize);
    }
}
