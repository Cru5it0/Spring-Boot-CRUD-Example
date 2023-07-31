
DROP DATABASE employee;
CREATE DATABASE employee;
USE employee;

CREATE TABLE person (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    last_name VARCHAR(50),
    age INT
);

CREATE TABLE email (
	id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    password VARCHAR(20)
);

CREATE TABLE sales (
	id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50)
);

CREATE TABLE employee (
	id INT PRIMARY KEY AUTO_INCREMENT,
    job VARCHAR(50),
    id_email INT,
    id_person INT,
    id_sales INT,
    status INT,
    FOREIGN KEY (id_email) REFERENCES email(id),
    FOREIGN KEY (id_person) REFERENCES person(id),
    FOREIGN KEY (id_sales) REFERENCES sales(id)
);

CREATE TABLE customer (
	id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50),
    id_email INT,
    id_person INT,
    status INT,
    FOREIGN KEY (id_email) REFERENCES email(id),
    FOREIGN KEY (id_person) REFERENCES person(id)
);

SELECT * FROM person;
SELECT * FROM employee;
SELECT * FROM email;
SELECT * FROM sales;
SELECT * FROM customer;



