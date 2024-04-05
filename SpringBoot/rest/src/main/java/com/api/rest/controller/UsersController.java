package com.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.api.rest.model.Users;
import com.api.rest.services.UserService;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }
}


/*
@RestController indicates that this class handles HTTP requests and automatically serializes the returned objects into JSON/XML response.
@RequestMapping("/api/users") specifies the base URL for this controller.
@Autowired is used to inject the UserService bean into the controller.
@GetMapping, @PostMapping, @DeleteMapping, and @PutMapping annotations are used to map HTTP GET, POST, DELETE, and PUT requests to controller methods respectively.
@PathVariable is used to extract parameters from the URL path.

*/