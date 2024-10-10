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

To test your Spring Boot REST controller using Postman, follow these steps:

### 1.Start Your Spring Boot Application
   Ensure your Spring Boot application is running. If you're using the default settings, it will run on `http://localhost:8080`.

### 2.Testing the REST Endpoints with Postman

#### 1.GET All Users
   - **Request Type**: `GET`
   - **URL**: `http://localhost:8080/api/users`
   - **Description**: This will fetch the list of all users.
   - **Steps**:
     - Open Postman.
     - Select `GET` from the request method dropdown.
     - Enter `http://localhost:8080/api/users` in the URL field.
     - Click **Send**.

#### 2.GET User by ID
   - **Request Type**: `GET`
   - **URL**: `http://localhost:8080/api/users/{id}`
   - **Description**: This will fetch a user by its ID.
   - **Steps**:
     - Select `GET` from the request method dropdown.
     - Enter `http://localhost:8080/api/users/1` (replace `1` with the actual user ID) in the URL field.
     - Click **Send**.

#### 3.POST Create a New User
   - **Request Type**: `POST`
   - **URL**: `http://localhost:8080/api/users`
   - **Description**: This will create a new user.
   - **Steps**:
     - Select `POST` from the request method dropdown.
     - Enter `http://localhost:8080/api/users` in the URL field.
     - Go to the **Body** tab.
     - Select **raw** and choose `JSON` from the dropdown.
     - Enter the JSON object in the text box like this:

       ```json
       {
         "username": "Avantika",
         "password": "password123",
         "email": "avantika@example.com"
       }
       ```

     - Click **Send**.

#### 4.PUT Update an Existing User
   - **Request Type**: `PUT`
   - **URL**: `http://localhost:8080/api/users/{id}`
   - **Description**: This will update the user with the given ID.
   - **Steps**:
     - Select `PUT` from the request method dropdown.
     - Enter `http://localhost:8080/api/users/1` (replace `1` with the actual user ID) in the URL field.
     - Go to the **Body** tab.
     - Select **raw** and choose `JSON`.
     - Enter the JSON object with the updated values:

       ```json
         {
         "username": "Avantika",
         "password": "new_password123",
         "email": "update_avantika@example.com"
       }
       ```

     - Click **Send**.

#### 5.DELETE a User by ID
   - **Request Type**: `DELETE`
   - **URL**: `http://localhost:8080/api/users/{id}`
   - **Description**: This will delete the user with the given ID.
   - **Steps**:
     - Select `DELETE` from the request method dropdown.
     - Enter `http://localhost:8080/api/users/1` (replace `1` with the actual user ID) in the URL field.
     - Click **Send**.

*/