package com.api.rest.services;

import java.util.List;
import java.util.Optional;

import com.api.rest.model.Users;

public interface UserService {
    List<Users> getAllUsers();
    Optional<Users> getUserById(Long id);
    Optional<Users> getUserByName(String username);
    Optional<Users> getUserByEmail(String email);
    Users createUser(Users user);
    void deleteUser(Long id);
    Users updateUser(Long id, Users user);
}
