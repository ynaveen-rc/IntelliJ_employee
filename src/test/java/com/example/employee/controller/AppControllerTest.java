package com.example.employee.controller;

import com.example.employee.AppStubs;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.ResponseDto;
import com.example.employee.dto.UserDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;
import com.example.employee.model.User;
import com.example.employee.service.EmployeeServiceImpl;
import com.example.employee.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppControllerTest {
    @Autowired
    AppController appController;
    @MockBean
    UserServiceImpl userServiceImpl;
    @MockBean
    EmployeeServiceImpl employeeServiceImpl;

    User expectedUser = AppStubs.expectedUser;
    User actualUser = AppStubs.actualUser;
    List<Employee> expectedList = AppStubs.expectedEmployeeList;
    List<Employee> actualList = AppStubs.actualEmployeeList;

    @Test
    public void createUserTest() {
        UserDto userDto = new UserDto("green", "222");
        ResponseDto<User> responseDto = new ResponseDto<>(null, HttpStatus.CREATED.value());
        try {
            when(userServiceImpl.saveUser(userDto)).thenReturn(actualUser);
            assertThat(responseDto.equals(appController.createUser(userDto))).isEqualTo(true);
        } catch (AppGeneralException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void getAllEmployeeTest() {
        String manager = "green";
        Authentication authentication = new UsernamePasswordAuthenticationToken(manager, null, new ArrayList<>());
        ResponseDto<List<Employee>> responseDto = new ResponseDto<>(expectedList, HttpStatus.OK.value());
        when(employeeServiceImpl.getAllEmployeeByManager(manager)).thenReturn(actualList);
        assertThat(responseDto.equals(appController.getAllEmployee(authentication))).isEqualTo(true);
    }

    @Test
    public void getEmployeeTest() {
        ResponseDto<Employee> responseDto = new ResponseDto<>(expectedList.get(0), HttpStatus.OK.value());
        try {
            when(employeeServiceImpl.getEmployeeById(1)).thenReturn(actualList.get(0));
            assertThat(responseDto.equals(appController.getEmployee(1))).isEqualTo(true);
        } catch (AppGeneralException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void addEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto("asd", "asd@demo.co", "it", "green");
        ResponseDto<Employee> responseDto = new ResponseDto<>(expectedList.get(0), HttpStatus.CREATED.value());
        when(employeeServiceImpl.saveEmployee(employeeDto)).thenReturn(actualList.get(0));
        assertThat(responseDto.equals(appController.addEmployee(employeeDto))).isEqualTo(true);
    }

    @Test
    public void updateEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto("dsa", "dsa@demo.co", "it", "green");
        ResponseDto<Employee> responseDto = new ResponseDto<>(expectedList.get(2), HttpStatus.CREATED.value());
        try {
            when(employeeServiceImpl.updateEmployeeById(1, employeeDto)).thenReturn(actualList.get(2));
            assertThat(responseDto.equals(appController.updateEmployee(1, employeeDto))).isEqualTo(true);
        } catch (AppGeneralException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void deleteEmployeeTest() {
        ResponseDto<Employee> responseDto = new ResponseDto<>(null, HttpStatus.OK.value());
        try {
            when(employeeServiceImpl.deleteEmployeeById(1)).thenReturn(actualList.get(0));
            assertThat(responseDto.equals(appController.deleteEmployee(1))).isEqualTo(true);
        } catch (AppGeneralException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }
}