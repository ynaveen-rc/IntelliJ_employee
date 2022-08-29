package com.example.employee.service;

import com.example.employee.dto.UserDto;
import com.example.employee.exception.AppGeneralException;
import com.example.employee.model.User;

public interface UserService {

    void saveUser(UserDto userDto) throws AppGeneralException;

    User getUserById(String id);
}
