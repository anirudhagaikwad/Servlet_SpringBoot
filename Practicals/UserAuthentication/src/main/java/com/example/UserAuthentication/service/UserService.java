package com.example.UserAuthentication.service;

import com.example.UserAuthentication.model.Auth;
import com.example.UserAuthentication.model.Group;
import com.example.UserAuthentication.model.User;
import com.example.UserAuthentication.repo.AuthRepository;
import com.example.UserAuthentication.repo.GroupRepository;
import com.example.UserAuthentication.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final AuthRepository authRepository;

    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.authRepository = authRepository;
    }

    // Implement the loadUserByUsername method from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getGroup() != null ? user.getGroup().getGroupName() : "USER") // Set role based on group
                .build();
    }

    @Transactional
    public User registerUser(User user, Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            User savedUser = userRepository.save(user);

            Auth auth = new Auth();
            auth.setUser(savedUser);
            auth.setGroup(group.get());
            authRepository.save(auth);

            return savedUser;
        } else {
            throw new RuntimeException("Group not found!");
        }
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setMobileNumber(updatedUser.getMobileNumber());
            existingUser.setAlternateMobile(updatedUser.getAlternateMobile());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAadharNumber(updatedUser.getAadharNumber());
            existingUser.setPanNumber(updatedUser.getPanNumber());
            existingUser.setPrimaryAddress(updatedUser.getPrimaryAddress());
            existingUser.setSecondaryAddress(updatedUser.getSecondaryAddress());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found!");
        }
    }

    @Transactional
    public void deleteUser(Long userId) {
        authRepository.findByUserId(userId).ifPresent(authRepository::delete);
        userRepository.deleteById(userId);
    }

    public Group findGroupByUserId(Long userId) {
        Optional<Auth> auth = authRepository.findByUserId(userId);
        return auth.map(Auth::getGroup).orElse(null);
    }
}
