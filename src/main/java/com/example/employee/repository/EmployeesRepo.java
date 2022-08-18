package com.example.employee.repository;

import com.example.employee.entity.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends MongoRepository<Employees, Integer> {
    List<Employees> findByManager(String manager);

}
