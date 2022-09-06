package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.ResponseDto;
import com.example.employee.dto.UserDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;
import com.example.employee.model.User;
import com.example.employee.service.EmployeeServiceImpl;
import com.example.employee.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/newuser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<User> createUser(@RequestBody UserDto userDto) throws AppGeneralException {
        User user = userServiceImpl.saveUser(userDto);
        return new ResponseDto<>(null, HttpStatus.CREATED.value());
    }

    @PostMapping("/login")
    public ResponseDto<User> login(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return new ResponseDto<>(null, HttpStatus.OK.value());
        }
        return new ResponseDto<>(null, HttpStatus.UNAUTHORIZED.value());
    }

    @PostMapping("/logout")
    public void logout(Authentication authentication) {
    }

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<Employee>> getAllEmployee(Authentication authentication) {
        List<Employee> list = employeeServiceImpl.getAllEmployeeByManager(authentication.getName());
        return new ResponseDto<>(list, HttpStatus.OK.value());
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Employee> getEmployee(@PathVariable("id") Integer id) throws AppGeneralException {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        return new ResponseDto<>(employee, HttpStatus.OK.value());
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<Employee> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeServiceImpl.saveEmployee(employeeDto);
        return new ResponseDto<>(employee, HttpStatus.CREATED.value());
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Employee> updateEmployee(@PathVariable("id") Integer id,
                                                @RequestBody EmployeeDto employeeDto) throws AppGeneralException {
        Employee updateEmp = employeeServiceImpl.updateEmployeeById(id, employeeDto);
        return new ResponseDto<>(updateEmp, HttpStatus.CREATED.value());
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Employee> deleteEmployee(@PathVariable("id") Integer id) throws AppGeneralException {
        Employee employee = employeeServiceImpl.deleteEmployeeById(id);
        return new ResponseDto<>(null, HttpStatus.OK.value());
    }

}
