package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {
    List<Employee> findByManager(String manager);

}
