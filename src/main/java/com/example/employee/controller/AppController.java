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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseDto createUser(@RequestBody UserDto userDto) throws AppGeneralException {
        User user = userServiceImpl.saveUser(userDto);
        return new ResponseDto(null, HttpStatus.CREATED.value());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(new ResponseDto(null, HttpStatus.OK.value()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(null, HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/performLogout")
    public ResponseEntity<Object> logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(new ResponseDto(null, HttpStatus.OK.value()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(null, HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto getAllEmployee(Authentication authentication) {
        List<Employee> list = employeeServiceImpl.getAllEmployeeByManager(authentication.getName());
        return new ResponseDto(list, HttpStatus.OK.value());
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto getEmployee(@PathVariable("id") Integer id) throws AppGeneralException {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        return new ResponseDto(employee, HttpStatus.OK.value());
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeServiceImpl.saveEmployee(employeeDto);
        return new ResponseDto(employee, HttpStatus.CREATED.value());
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto updateEmployee(@PathVariable("id") Integer id,
                                      @RequestBody EmployeeDto employeeDto) throws AppGeneralException {
        Employee updateEmp = employeeServiceImpl.updateEmployeeById(id, employeeDto);
        return new ResponseDto(updateEmp, HttpStatus.OK.value());
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto deleteEmployee(@PathVariable("id") Integer id) throws AppGeneralException {
        Employee employee = employeeServiceImpl.deleteEmployeeById(id);
        return new ResponseDto(null, HttpStatus.OK.value());
    }

}
