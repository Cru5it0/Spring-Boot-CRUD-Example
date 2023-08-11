package org.bbva.example.services;

import org.bbva.example.errors.ItemNotFoundException;
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

    public Customer getIdCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer newCustomer, Long id) {
        return customerRepository.findById(id)
                .map(item -> {
                    item.setAddress(newCustomer.getAddress());
                    item.setStatus(newCustomer.getStatus());
                    return customerRepository.save(item);
                }).orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    public Customer updateCustomer(Customer updateCustomer) {
        return customerRepository.save(updateCustomer);
    }

    public boolean delete(Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
            customer.setStatus(0);
            customerRepository.save(customer);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
