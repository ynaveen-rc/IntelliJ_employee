package com.example.employee.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "employee")
public class Employee {
    @Transient
    public static final String SEQUENCE_NAME = "empSequence";
    @MongoId
    private int id;
    private String empName;
    private String empMail;
    private String department;
    private String manager;

    public Employee() {

    }

    public Employee(int id, String empName, String empMail, String department, String manager) {
        this.id = id;
        this.empName = empName;
        this.empMail = empMail;
        this.department = department;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empMail='" + empMail + '\'' +
                ", department='" + department + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
