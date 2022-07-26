package com.example.employee;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, String> {
}
