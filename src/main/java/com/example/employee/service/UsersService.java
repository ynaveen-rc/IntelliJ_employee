package com.example.employee.service;

import com.example.employee.entity.Users;
import com.example.employee.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersRepo usersrepo;

    public void saveUser(Users user) {
        usersrepo.save(user);
    }

    public Users getUserByUsername(String username) {
        return usersrepo.findById(username).orElse(null);
    }
}
