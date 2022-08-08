package com.example.employee.repository;

import com.example.employee.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer> {
    List<Employees> findByManager(String manager);

}
