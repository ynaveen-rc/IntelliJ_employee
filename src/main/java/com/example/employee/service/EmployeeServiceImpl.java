package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.PageEmployeeDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    EmployeeSequenceService employeeSequenceService;
    @Autowired
    MongoTemplate mongoTemplate;

//    @Override
//    public PageEmployeeDto getAllEmployeeByManager(String manager, int pageNo, int pageSize, String sortBy, String sortDirection) {
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
//                sortDirection.equals("descending") ? Sort.by(sortBy).descending().and(Sort.by("id")) : Sort.by(sortBy).ascending().and(Sort.by("id")));
//        Page<Employee> page = employeeRepo.findByManager(manager, pageable);
//        return new PageEmployeeDto(page.getContent(), page.getNumber() + 1, page.getTotalPages(), page.getTotalElements(), pageSize);
//    }

    @Override
    public PageEmployeeDto getAllEmployeeByManager(int pageNo, int pageSize, String sortBy, String sortDirection, int id, String empName, String empMail, String department, String manager) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
                sortDirection.equals("descending") ? Sort.by(sortBy).descending().and(Sort.by("id")) : Sort.by(sortBy).ascending().and(Sort.by("id")));
        Query query = new Query();
        if (id != 0) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        if (!empName.equals("")) {
            query.addCriteria(Criteria.where("empName").regex(empName));
        }
        if (!empMail.equals("")) {
            query.addCriteria(Criteria.where("empMail").regex(empMail));
        }
        if (!department.equals("")) {
            query.addCriteria(Criteria.where("department").is(department));
        }
        query.addCriteria(Criteria.where("manager").is(manager));
        query.with(pageable);
        Page<Employee> page = PageableExecutionUtils.getPage(mongoTemplate.find(query, Employee.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Employee.class));
        return new PageEmployeeDto(page.getContent(), page.getNumber() + 1, page.getTotalPages(), page.getTotalElements(), pageSize);
    }

    @Override
    public Employee getEmployeeById(Integer id) throws AppGeneralException {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new AppGeneralException("EMPLOYEE_NOT_FOUND");
        }
        return employee;
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeSequenceService.getSequenceNumber(Employee.SEQUENCE_NAME));
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMail(employeeDto.getEmpMail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setManager(employeeDto.getManager());
        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployeeById(Integer id, EmployeeDto employeeDto) throws AppGeneralException {
        Employee updateEmp = employeeRepo.findById(id).orElse(null);
        if (updateEmp == null) {
            throw new AppGeneralException("EMPLOYEE_NOT_FOUND");
        }
        updateEmp.setEmpName(employeeDto.getEmpName());
        updateEmp.setEmpMail(employeeDto.getEmpMail());
        updateEmp.setDepartment(employeeDto.getDepartment());
        updateEmp.setManager(employeeDto.getManager());
        employeeRepo.save(updateEmp);
        return updateEmp;
    }

    @Override
    public Employee deleteEmployeeById(Integer id) throws AppGeneralException {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new AppGeneralException("EMPLOYEE_NOT_FOUND");
        }
        employeeRepo.delete(employee);
        return employee;
    }

}
