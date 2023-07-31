package org.bbva.example.services;

import org.bbva.example.model.Customer;
import org.bbva.example.reposiroty.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ArrayList<Customer> getAllCustomer() {
        return (ArrayList<Customer>)  customerRepository.findAll();
    }

    

}
