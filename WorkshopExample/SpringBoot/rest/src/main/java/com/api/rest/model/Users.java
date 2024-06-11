package com.api.rest.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    // Default constructor
    public Users() {
    }
    //Constructors
	public Users(Long id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Users(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	 // Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
    
}

/*
Annotations: The @Id annotation marks the id field as the primary key of the entity. The @GeneratedValue annotation specifies that the value for this field will be generated automatically by the underlying database. The GenerationType.IDENTITY strategy indicates that the database should automatically assign an ID when a new record is inserted.

Primary Key Generation: With GenerationType.IDENTITY, the database will typically use an auto-increment column to generate unique IDs for each record. This means that when you save a new Users object to the database, the database will automatically assign it a unique ID.

Constructor and Setter: Even though you've provided constructors that accept id, the value for id will be overridden by the database-generated ID when a new record is inserted. Similarly, the setter for id allows you to set the ID manually, but typically, you won't need to do this for new records since the database handles ID generation. 
*/