package com.example.employee.dto;

import org.springframework.stereotype.Component;

//@Component
public class UserDto {
    private String id;
    private String password;

    public UserDto() {
    }

    public UserDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
