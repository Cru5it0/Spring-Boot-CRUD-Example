package org.bbva.example.controller;

import org.bbva.example.model.Employee;
import org.bbva.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public ArrayList<Employee> getAllEmployee() {
        return (ArrayList<Employee>)  employeeService.getAllEmployee();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@Valid @RequestBody Employee newEmployee) {
        return this.employeeService.save(newEmployee);
    }

    @GetMapping("/employee/{id}")
    public Employee getIdEmployee(@PathVariable("id") Long id) {
        return employeeService.getIdEmployee(id);
    }

    @PutMapping("/employee/id")
    public Employee updateEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
        return this.employeeService.update(newEmployee, id);
    }

    @DeleteMapping("/employee/id")
    public String deleteEmployee(@PathVariable("id") Long id) {
        boolean success = this.employeeService.delete1(id);

        if(success) {
            return "Successful removal";
        } else {
            return "Deletion was not completed";
        }
    }

}
