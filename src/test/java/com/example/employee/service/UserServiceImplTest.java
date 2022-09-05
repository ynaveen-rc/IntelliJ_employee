package com.example.employee.service;

import com.example.employee.AppStubs;
import com.example.employee.dto.UserDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.User;
import com.example.employee.repository.UserRepo;
import com.example.employee.security.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

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
        assertThat(expectedUser.equals(userServiceImpl.getUserById(id))).isEqualTo(true);
    }

    @Test
    public void saveUserSuccessTest() {
        UserDto userDto = new UserDto("green", "222");
        when(userRepo.save(actualUser)).thenReturn(actualUser);
        try {
            assertThat(expectedUser.equals(userServiceImpl.saveUser(userDto))).isEqualTo(true);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
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
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void saveUserFailureTwoTest() {
        String id = "green";
        UserDto userDto = new UserDto(id, "222");
        when(userRepo.findById(id)).thenReturn(Optional.of(actualUser));
        when(userRepo.save(actualUser)).thenReturn(actualUser);
        try {
            boolean equality = expectedUser.equals(actualUser);
            if (!equality) {
                fail();
            }
            userServiceImpl.saveUser(userDto);
        } catch (AppGeneralException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void loadUserByUsernameSuccessTest() {
        String id = "green";
        when(userRepo.findById(id)).thenReturn(Optional.ofNullable(actualUser));
        UserDetails expectedUserDetails = new UserDetailsImpl(expectedUser);
        UserDetails actualUserDetails = userServiceImpl.loadUserByUsername(id);
        assertThat(expectedUserDetails.equals(actualUserDetails)).isEqualTo(true);
    }

    @Test
    public void loadUserByUsernameFailureTest() {
        String id = "green";
        when(userRepo.findById(id)).thenReturn(Optional.ofNullable(actualUser));
        try {
            userServiceImpl.loadUserByUsername("white");
            fail();
        } catch (UsernameNotFoundException ex) {
            assertThat(ex.getMessage()).isNotEmpty();
        }
    }
}