-- Create the database
CREATE DATABASE IF NOT EXISTS employee;

-- Use the database
USE employee;

-- Create the empdetails table
CREATE TABLE IF NOT EXISTS empdetails (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    hire_date DATE NOT NULL,
    salary DECIMAL(10, 2)
);
