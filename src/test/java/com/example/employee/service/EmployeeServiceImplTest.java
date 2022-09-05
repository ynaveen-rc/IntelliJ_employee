package com.example.employee.service;

import com.example.employee.AppStubs;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;
    @MockBean
    EmployeeSequenceService employeeSequenceService;
    @MockBean
    EmployeeRepo employeeRepo;

    List<Employee> expectedList = AppStubs.expectedEmployeeList;
    List<Employee> actualList = AppStubs.actualEmployeeList;

    @Test
    public void getAllEmployeeByManagerTest() {
        String manager = "green";
        when(employeeRepo.findByManager(manager)).thenReturn(actualList);
        List<Employee> list = employeeServiceImpl.getAllEmployeeByManager(manager);
        assertThat(expectedList.get(0).equals(list.get(0))).isEqualTo(true);
        assertThat(expectedList.get(1).equals(list.get(1))).isEqualTo(true);
    }

    @Test
    public void getEmployeeByIdSuccessTest() {
        Integer id = 1;
        when(employeeRepo.findById(id)).thenReturn(Optional.ofNullable(actualList.get(0)));
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(id);
            assertThat(expectedList.get(0).equals(employee)).isEqualTo(true);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void getEmployeeByIdFailureTest() {
        when(employeeRepo.findById(1)).thenReturn(Optional.ofNullable(actualList.get(0)));
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(2);
            fail();
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void saveEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto("asd", "asd@demo.co", "it", "green");
        Employee employee = actualList.get(0);
        when(employeeRepo.save(employee)).thenReturn(employee);
        when(employeeSequenceService.getSequenceNumber(Employee.SEQUENCE_NAME)).thenReturn(1);
        assertThat(expectedList.get(0).equals(employeeServiceImpl.saveEmployee(employeeDto))).isEqualTo(true);
    }

    @Test
    public void updateEmployeeByIdSuccessTest() {
        Integer id = 1;
        EmployeeDto employeeDto = new EmployeeDto("dsa", "dsa@demo.co", "it", "green");
        Employee employee = actualList.get(2);
        when(employeeRepo.findById(id)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepo.save(employee)).thenReturn(employee);
        try {
            Employee updateEmp = employeeServiceImpl.updateEmployeeById(id, employeeDto);
            assertThat(expectedList.get(2).equals(updateEmp)).isEqualTo(true);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void updateEmployeeByIdFailureTest() {
        EmployeeDto employeeDto = new EmployeeDto("dsa", "dsa@demo.co", "it", "green");
        Employee employee = actualList.get(2);
        when(employeeRepo.findById(1)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepo.save(employee)).thenReturn(employee);
        try {
            Employee updateEmp = employeeServiceImpl.updateEmployeeById(2, employeeDto);
            fail();
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void deleteEmployeeByIdSuccessTest() {
        Integer id = 1;
        Employee employee = actualList.get(0);
        when(employeeRepo.findById(id)).thenReturn(Optional.ofNullable(employee));
        doNothing().when(employeeRepo).delete(employee);
        try {
            employeeServiceImpl.deleteEmployeeById(id);
            verify(employeeRepo, times(1)).delete(employee);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void deleteEmployeeByIdFailureTest() {
        Employee employee = actualList.get(0);
        when(employeeRepo.findById(1)).thenReturn(Optional.ofNullable(employee));
        doNothing().when(employeeRepo).delete(employee);
        try {
            employeeServiceImpl.deleteEmployeeById(2);
            fail();
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }
}