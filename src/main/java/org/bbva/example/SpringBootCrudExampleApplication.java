package org.bbva.example;

import org.bbva.example.model.Email;
import org.bbva.example.model.Employee;
import org.bbva.example.model.Person;
import org.bbva.example.model.Sales;
import org.bbva.example.reposiroty.EmailRepository;
import org.bbva.example.reposiroty.EmployeeRepository;
import org.bbva.example.reposiroty.PersonRepository;
import org.bbva.example.reposiroty.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExampleApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SalesRepository salesRepository;
	@Autowired
	EmailRepository emailRepository;

	@Override
	public void run(String... args) throws Exception {

		// Add new People
		Person p1 = new Person(null, "Cruz Isaac", "Aranda", 23);
		Person p2 = new Person(null, "Paulina", "Cervantes", 19);
		Person p3 = new Person(null, "Oscar", "Medina", 21);
		personRepository.save(p1);
		personRepository.save(p2);
		personRepository.save(p3);

		// Add new Emails
		Email email1 = new Email(null, "curz@hotmail.com", "12345");
		Email email2 = new Email(null, "paulina@hotmail.com", "12345");
		Email email3 = new Email(null, "oscar@hotmail.com", "12345");

		// Add new Sales
		Sales s1 = new Sales(null, "Sold clothes");
		Sales s2 = new Sales(null, "Recruit employees");
		Sales s3 = new Sales(null, "Sold clothes");

		// Add new Employees
		Employee e1 = new Employee(null, "Staff", email1, p1, s1, 1);
		Employee e2 = new Employee(null, "Human Resources", email1, p2, s2, 1);
		Employee e3 = new Employee(null, "Staff", email1, p3, s3, 1);
	}

}
