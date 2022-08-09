package com.example.employee.service;

import com.example.employee.entity.Users;
import com.example.employee.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepo usersrepo;

    @Override
    public void saveUser(Users user) {
        usersrepo.save(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        return usersrepo.findById(username).orElse(null);
    }
}
