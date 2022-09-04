package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppStubs {
    public static List<Employee> expectedEmployeeList =
            Stream.of(new Employee(1, "asd", "asd@demo.co", "it", "green"),
                    new Employee(2, "zxc", "zxc@demo.co", "it", "green"),
                    new Employee(1, "dsa", "dsa@demo.co", "it", "green")).collect(Collectors.toList());
    public static List<Employee> actualEmployeeList =
            Stream.of(new Employee(1, "asd", "asd@demo.co", "it", "green"),
                    new Employee(2, "zxc", "zxc@demo.co", "it", "green"),
                    new Employee(1, "dsa", "dsa@demo.co", "it", "green")).collect(Collectors.toList());
    public static User expectedUser = new User("green", "222");
    public static User actualUser = new User("green", "222");
}
