package com.api.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.rest.model.Users;
import com.api.rest.repo.UsersRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<Users> getUserByName(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users updateUser(Long id, Users user) {
        if (usersRepository.existsById(id)) {
            user.setId(id); // Ensure the ID is set correctly
            return usersRepository.save(user);
        }
        return null; // Or throw an exception indicating user not found
    }
}
