package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.PageEmployeeDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;

public interface EmployeeService {

//    PageEmployeeDto getAllEmployeeByManager(String manager, int pageNo, int pageSize, String sortBy, String sortDirection);

    PageEmployeeDto getAllEmployeeByManager(int pageNo, int pageSize, String sortBy, String sortDirection, int id, String empName, String empMail, String department, String manager);

    Employee getEmployeeById(Integer id) throws AppGeneralException;

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployeeById(Integer id, EmployeeDto employeeDto) throws AppGeneralException;

    Employee deleteEmployeeById(Integer id) throws AppGeneralException;
}
