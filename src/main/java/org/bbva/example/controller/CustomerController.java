package org.bbva.example.controller;

import org.bbva.example.model.Customer;
import org.bbva.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class CustomerController {

    @Autowired(required = false)
    CustomerService customerService;

    @GetMapping(value = "/customer")
    public ArrayList<Customer> getAllCustomer() {
        return (ArrayList<Customer>) customerService.getAllCustomer();
    }

    @PostMapping(value = "/customer")
    public Customer addCustomer(@Valid @RequestBody Customer newCustumer) {
        return this.customerService.save(newCustumer);
    }

    @GetMapping(value = "/customer/{id}")
    public Customer getIdCustomer(@PathVariable("id") Long id) {
        return customerService.getIdCustomer(id);
    }

    @PutMapping(value = "/customer/{id}")
    public Customer updateCustomer(@Valid @RequestBody Customer newCustomer, @PathVariable Long id) {
        return this.customerService.update(newCustomer, id);
    }

    @DeleteMapping(value = "/customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        boolean success = this.customerService.delete(id);

        if (success) {
            return "Customer with id " + id + " was disabled";
        }else {
            return "Unable to disable customer with id " + id;
        }
    }

}
