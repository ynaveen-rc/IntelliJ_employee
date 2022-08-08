package com.example.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name = "employee")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empid;
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

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
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
        return "Employees{" + "empid=" + empid + ", empname='" + empname + '\'' + ", empmail='" + empmail + '\'' + ", department='" + department + '\'' + ", manager='" + manager + '\'' + '}';
    }
}
