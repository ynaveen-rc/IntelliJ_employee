package com.example.employee.service;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    EmployeeSequenceService employeeSequenceService;

    @Override
    public List<Employee> getAllEmployeeByManager(String manager) {
        return employeeRepo.findByManager(manager);
    }

    @Override
    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
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
    public Employee updateEmployeeById(Integer id, EmployeeDto employeeDto) throws EmployeeNotFoundException {
        Employee updateEmp = employeeRepo.findById(id).orElse(null);
        if (updateEmp == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        updateEmp.setEmpName(employeeDto.getEmpName());
        updateEmp.setEmpMail(employeeDto.getEmpMail());
        updateEmp.setDepartment(employeeDto.getDepartment());
        updateEmp.setManager(employeeDto.getManager());
        employeeRepo.save(updateEmp);
        return updateEmp;
    }

    @Override
    public void deleteEmployeeById(Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepo.delete(employee);
    }

}
