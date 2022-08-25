package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployeeByManager(String manager);

    Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployeeById(Integer id, EmployeeDto employeeDto) throws EmployeeNotFoundException;

    void deleteEmployeeById(Integer id) throws EmployeeNotFoundException;
}
