package com.example.employee.service;

import com.example.employee.dto.UserDto;
import com.example.employee.model.User;
import com.example.employee.repository.UserRepo;
import com.example.employee.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        userRepo.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    // Method related to Spring Security
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user);
    }
}
