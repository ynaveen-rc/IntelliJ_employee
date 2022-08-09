package com.example.employee.service;

import com.example.employee.entity.Users;

public interface UsersService {

    void saveUser(Users user);

    Users getUserByUsername(String username);
}
