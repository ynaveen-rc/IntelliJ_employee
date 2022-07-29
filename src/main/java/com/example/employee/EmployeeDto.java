package com.example.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDto {

    private Integer empid;
    private String empname;
    private String empmail;
    private String department;
    private String manager;

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
        return "EmployeeDto{" + "empname='" + empname + '\'' + ", empmail='" + empmail + '\'' + ", department='" + department + '\'' + ", manager='" + manager + '\'' + '}';
    }
}
