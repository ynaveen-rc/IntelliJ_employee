package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployeeByManager(String manager);

    Employee getEmployeeById(Integer id) throws AppGeneralException;

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployeeById(Integer id, EmployeeDto employeeDto) throws AppGeneralException;

    Employee deleteEmployeeById(Integer id) throws AppGeneralException;
}
