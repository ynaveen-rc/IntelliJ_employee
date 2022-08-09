package com.example.employee.service;

import com.example.employee.entity.Employees;
import com.example.employee.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeesRepo emprepo;

    @Override
    public List<Employees> getEmployee(String username) {
        return emprepo.findByManager(username);
    }

    @Override
    public void saveEmployee(Employees employee) {
        emprepo.save(employee);
    }

    @Override
    public Employees getEmployeeById(Integer empid) {
        return emprepo.findById(empid).orElse(null);
    }

    @Override
    public void deleteEmployeeById(Integer empid) {
        emprepo.deleteById(empid);
    }
}
