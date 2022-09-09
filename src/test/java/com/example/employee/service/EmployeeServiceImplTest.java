package com.example.employee.service;

import com.example.employee.AppStubs;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepo;
import com.example.employee.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.support.PageableExecutionUtils;

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
    @MockBean
    UserServiceImpl userServiceImpl;
    @MockBean
    UserRepo userRepo;
    @MockBean
    GridFsTemplate gridFsTemplate;
    @MockBean
    MongoTemplate mongoTemplate;

    List<Employee> expectedList = AppStubs.expectedEmployeeList;
    List<Employee> actualList = AppStubs.actualEmployeeList;

//    @Test
//    public void getAllEmployeeByManagerTest() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(1));
//        query.addCriteria(Criteria.where("empName").regex("a"));
//        query.addCriteria(Criteria.where("empMail").regex("a"));
//        query.addCriteria(Criteria.where("department").is("it"));
//        query.addCriteria(Criteria.where("manager").is("green"));
//        query.with(pageable);
//        when(mongoTemplate.find(query, Employee.class)).thenReturn(actualList);
//        when(mongoTemplate.count(query.skip(0).limit(0), Employee.class)).thenReturn(3L);
//        when(PageableExecutionUtils.getPage(mongoTemplate.find(query, Employee.class), pageable, () -> mongoTemplate.count(query.skip(0).limit(0), Employee.class)).getContent())
//                .thenReturn(actualList);
//        assertThat(expectedList
//                .equals(employeeServiceImpl.getAllEmployeeByManager(1, 10, "id", "ascending", 1, "a", "a", "it", "green").getContent()))
//                .isEqualTo(true);
//    }

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