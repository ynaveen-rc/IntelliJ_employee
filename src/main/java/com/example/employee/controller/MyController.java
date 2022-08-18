package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.Employees;
import com.example.employee.entity.Users;
import com.example.employee.service.EmployeeSequenceService;
import com.example.employee.service.EmployeeServiceImpl;
import com.example.employee.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    UsersServiceImpl usersServiceImpl;
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    EmployeeSequenceService employeeSequenceService;

    @PostMapping("/newuser")
    public Users createuser(@RequestBody Users users) {
        if (!users.getId().isEmpty() && !users.getPassword().isEmpty()) {
            usersServiceImpl.saveUser(users);
        }
        return users;
    }

    @PostMapping("/login")
    public HttpStatus login(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
    }

    @GetMapping("/employee")
    public List<Employees> getallemployee(Authentication authentication) {
        List<Employees> list = employeeServiceImpl.getEmployee(authentication.getName());
        return list;
    }

    @GetMapping("/employee/{empid}")
    public Employees getemployee(@PathVariable("empid") Integer empid) {
        Employees employee = employeeServiceImpl.getEmployeeById(empid);
        return employee;
    }

    @PostMapping("/employee")
    public Employees addemployee(@RequestBody Employees employee) {
        employee.setId(employeeSequenceService.getSequenceNumber(Employees.SEQUENCE_NAME));
        employeeServiceImpl.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employee/{empid}")
    public Employees updateemployee(@PathVariable("empid") Integer empid,
                                    @RequestBody EmployeeDto empdto) {
        Employees updateemp = employeeServiceImpl.getEmployeeById(empid);
        updateemp.setEmpname(empdto.getEmpname());
        updateemp.setEmpmail(empdto.getEmpmail());
        updateemp.setDepartment(empdto.getDepartment());
        updateemp.setManager(empdto.getManager());
        employeeServiceImpl.saveEmployee(updateemp);
        return updateemp;
    }

    @DeleteMapping("/employee/{empid}")
    public Employees deleteemployee(@PathVariable("empid") Integer empid) {
        Employees employee = employeeServiceImpl.getEmployeeById(empid);
        employeeServiceImpl.deleteEmployee(employee);
        return employee;
    }

}
