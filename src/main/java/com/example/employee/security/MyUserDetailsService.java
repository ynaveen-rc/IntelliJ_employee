package com.example.employee.security;

import com.example.employee.entity.Users;
import com.example.employee.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user);
    }
}
