package com.example.employee.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "employees")
public class Employees {
    @Transient
    public static final String SEQUENCE_NAME = "empSequence";
    @MongoId
    private int id;
    private String empname;
    private String empmail;
    private String department;
    private String manager;

    public Employees() {

    }

    public Employees(String empname, String empmail, String department, String manager) {
        this.empname = empname;
        this.empmail = empmail;
        this.department = department;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpmail() {
        return empmail;
    }

    public void setEmpmail(String empmail) {
        this.empmail = empmail;
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
        return "Employees{" + "id=" + id + ", empname='" + empname + '\'' + ", empmail='" + empmail + '\'' + ", department='" + department + '\'' + ", manager='" + manager + '\'' + '}';
    }
}
