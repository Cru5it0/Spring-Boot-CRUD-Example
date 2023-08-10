package org.bbva.example;

import org.bbva.example.model.*;
import org.bbva.example.reposiroty.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExampleApplication.class, args);
	}

	@Autowired(required = false)
	PersonRepository personRepository;
	@Autowired(required = false)
	EmployeeRepository employeeRepository;
	@Autowired(required = false)
	SalesRepository salesRepository;
	@Autowired(required = false)
	EmailRepository emailRepository;
	@Autowired(required = false)
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		// Add new People
		Person p1 = new Person(null, "Cruz Isaac", "Aranda", 23);
		Person p2 = new Person(null, "Paulina", "Cervantes", 19);
		Person p3 = new Person(null, "Oscar", "Medina", 21);
		personRepository.save(p1);
		personRepository.save(p2);
		personRepository.save(p3);

		Person p4 = new Person(null, "Sofia Saharao", "Cervantes", 23);
		Person p5 = new Person(null, "Isaac", "Cervantes", 19);
		Person p6 = new Person(null, "Daniel", "Rodriguez", 21);
		personRepository.save(p4);
		personRepository.save(p5);
		personRepository.save(p6);

		// Add new Emails employees
		Email email1 = new Email(null, "curz@hotmail.com", "12345");
		Email email2 = new Email(null, "paulina@hotmail.com", "12345");
		Email email3 = new Email(null, "oscar@hotmail.com", "12345");
		emailRepository.save(email1);
		emailRepository.save(email2);
		emailRepository.save(email3);

		// Add new Emails customers
		Email email4 = new Email(null, "sofia@gmail.com", "12345");
		Email email5 = new Email(null, "isaac@gmail.com", "12345");
		Email email6 = new Email(null, "daniel@gmail.com", "12345");
		emailRepository.save(email4);
		emailRepository.save(email5);
		emailRepository.save(email6);

		// Add new Sales
		Sales s1 = new Sales(null, "Sold clothes");
		Sales s2 = new Sales(null, "Recruit employees");
		Sales s3 = new Sales(null, "Sold clothes");
		salesRepository.save(s1);
		salesRepository.save(s2);
		salesRepository.save(s3);

		// Add new Employees
		Employee e1 = new Employee(null, "Staff", email1, p1, s1, 1);
		Employee e2 = new Employee(null, "Human Resources", email1, p2, s2, 1);
		Employee e3 = new Employee(null, "Staff", email1, p3, s3, 1);
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		employeeRepository.save(e3);

		// Add new Customer
		Customer c1 = new Customer(null, "Faro del Carmen 239", email4, p4, 1);
		Customer c2 = new Customer(null, "San Juan Bosco 200", email5, p5, 1);
		Customer c3 = new Customer(null, "Vivar 512", email6, p6, 1);
		customerRepository.save(c1);
		customerRepository.save(c2);
		customerRepository.save(c3);
		 */
	}

}

