package com.example.SpringBootCRUD.crud;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

/*
The `EmployeeRepository` interface that extends `JpaRepository` in your code is part of the "Spring Data JPA" framework, which provides an abstraction layer over the database access code, 
allowing you to work with databases in a more efficient and less boilerplate manner.

### Purpose of `JpaRepository` and `EmployeeRepository`:

1.JpaRepository:
   - `JpaRepository` is a Spring Data JPA interface that extends `PagingAndSortingRepository` and `CrudRepository`. It provides several generic methods for performing CRUD (Create, Read, Update, Delete) operations on entities without the need to explicitly write SQL queries or boilerplate code.
   - By extending `JpaRepository`, you get access to built-in methods such as:
     - `save(S entity)`: Saves or updates the entity in the database.
     - `findById(ID id)`: Retrieves an entity by its ID.
     - `findAll()`: Retrieves all entities from the database.
     - `deleteById(ID id)`: Deletes the entity with the given ID.
     - `existsById(ID id)`: Checks if an entity with a given ID exists.
   - These methods work with **JPA entities** (like `Employee`) and use the entity's primary key type (`Long` in this case) for the CRUD operations.

2.EmployeeRepository:
   - By creating the `EmployeeRepository` interface and extending `JpaRepository<Employee, Long>`, you don't need to write custom queries or handle transactions explicitly for basic database operations. Spring Data JPA will automatically provide the implementations for the CRUD operations based on your `Employee` entity.
   - This saves a lot of time and effort by reducing boilerplate code that you would otherwise have to write manually.

### Why the Imports are Used:
- import org.springframework.data.jpa.repository.JpaRepository;:
   - This import brings in the `JpaRepository` interface, which provides CRUD methods and pagination/sorting functionality for your `Employee` entity. You use it to handle common database interactions (save, find, update, delete).
  
- import org.springframework.stereotype.Repository;:
   - The `@Repository` annotation is used to indicate that the class or interface is a repository (a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects).
   - Although Spring Data automatically detects repositories, the `@Repository` annotation is useful for catching platform-specific exceptions and converting them to Spring's unified `DataAccessException`.

### Benefits of Using `JpaRepository`:
- Automatic Query Generation: You don't need to write the implementation code for CRUD operations, as they are provided by Spring Data JPA.
- Custom Queries: You can still define custom methods for more complex queries using method naming conventions or custom JPQL (Java Persistence Query Language) queries.
- Consistency: The framework ensures that transaction management, session management, and exception handling are handled consistently across the application.

*/