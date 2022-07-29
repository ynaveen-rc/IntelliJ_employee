package com.example.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer> {
    List<Employees> findByManager(String manager);

}
