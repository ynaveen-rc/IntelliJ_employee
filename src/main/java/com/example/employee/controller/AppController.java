package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.UserDto;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeServiceImpl;
import com.example.employee.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/newuser")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        if (!userDto.getId().isEmpty() && !userDto.getPassword().isEmpty()) {
            userServiceImpl.saveUser(userDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee(Authentication authentication) {
        List<Employee> list = employeeServiceImpl.getAllEmployeeByManager(authentication.getName());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeServiceImpl.saveEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id,
                                                   @RequestBody EmployeeDto employeeDto) throws EmployeeNotFoundException {
        Employee updateEmp = employeeServiceImpl.updateEmployeeById(id, employeeDto);
        return new ResponseEntity<>(updateEmp, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
        employeeServiceImpl.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
