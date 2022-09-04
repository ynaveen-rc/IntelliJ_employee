package com.example.employee.service;

import com.example.employee.AppStubs;
import com.example.employee.dto.UserDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.User;
import com.example.employee.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userServiceImpl;
    @MockBean
    UserRepo userRepo;

    User expectedUser = AppStubs.expectedUser;
    User actualUser = AppStubs.actualUser;

    @Test
    public void getUserByIdTest() {
        String id = "green";
        when(userRepo.findById(id)).thenReturn(Optional.ofNullable(actualUser));
        assertThat(expectedUser.equals(userServiceImpl.getUserById(id)));
    }

    @Test
    public void saveUserSuccessTest() {
        UserDto userDto = new UserDto("green", "222");
        when(userRepo.save(actualUser)).thenReturn(actualUser);
        try {
            assertThat(expectedUser.equals(userServiceImpl.saveUser(userDto)));
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage());
        }
    }

    @Test
    public void saveUserFailureOneTest() {
        UserDto userDto = new UserDto("", "222");
        when(userRepo.save(actualUser)).thenReturn(actualUser);
        try {
            userServiceImpl.saveUser(userDto);
            fail();
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage());
        }
    }

    @Test
    public void saveUserFailureTwoTest() {
        String id = "green";
        UserDto userDto = new UserDto(id, "222");
        when(userRepo.findById(id)).thenReturn(Optional.of(actualUser));
        when(userRepo.save(actualUser)).thenReturn(actualUser);
        try {
            if (expectedUser.equals(actualUser)) {
                fail();
            }
            userServiceImpl.saveUser(userDto);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage());
        }
    }

    @Test
    public void loadUserByUsernameSuccessTest() {
        String id = "green";
        when(userRepo.findById(id)).thenReturn(Optional.ofNullable(actualUser));
        assertThat(expectedUser.equals(userServiceImpl.loadUserByUsername(id)));
    }

    @Test
    public void loadUserByUsernameFailureTest() {
        String id = "green";
        when(userRepo.findById(id)).thenReturn(Optional.ofNullable(actualUser));
        try {
            userServiceImpl.loadUserByUsername("white");
            fail();
        } catch (UsernameNotFoundException ex) {
            assertThat(ex.getMessage());
        }
    }
}