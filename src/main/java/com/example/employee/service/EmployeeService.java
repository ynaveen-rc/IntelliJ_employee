package com.example.employee.service;

import com.example.employee.entity.Employees;

import java.util.List;

public interface EmployeeService {

    List<Employees> getEmployee(String username);

    void saveEmployee(Employees employee);

    Employees getEmployeeById(Integer empid);

    void deleteEmployeeById(Integer empid);
}
