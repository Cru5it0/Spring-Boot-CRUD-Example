package org.bbva.example.services;

import org.bbva.example.errors.ItemNotFoundException;
import org.bbva.example.model.Employee;
import org.bbva.example.reposiroty.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ArrayList<Employee> getAllEmployee() {
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    public Employee getIdEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee newEmployee, Long id) {
        return employeeRepository.findById(id)
                .map(item -> {
                    item.setJob(newEmployee.getJob());
                    item.setStatus(newEmployee.getStatus());
                    return employeeRepository.save(item);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    public boolean delete1(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Employee delete2(Employee newEmployee, Long id) {
        return employeeRepository.findById(id)
                .map(item -> {
                    item.setStatus(0);
                    return employeeRepository.save(item);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

}
