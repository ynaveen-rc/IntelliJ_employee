package com.example.employee.dto;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDto {

    private String empName;
    private String empMail;
    private String department;
    private String manager;

    public EmployeeDto() {
    }

    public EmployeeDto(String empName, String empMail, String department, String manager) {
        this.empName = empName;
        this.empMail = empMail;
        this.department = department;
        this.manager = manager;
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
        return "EmployeeDto{" +
                "empName='" + empName + '\'' +
                ", empMail='" + empMail + '\'' +
                ", department='" + department + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
