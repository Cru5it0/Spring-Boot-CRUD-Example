package org.bbva.example.services;

import org.bbva.example.errors.ItemNotFoundException;
import org.bbva.example.model.Sales;
import org.bbva.example.reposiroty.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    public ArrayList<Sales> getAllSales() {
        return (ArrayList<Sales>) salesRepository.findAll();
    }

    public Sales getIdSales(Long id) {
        return salesRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Sales save(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales update(Sales newSales, Long id) {
        return salesRepository.findById(id)
                .map(sales -> {
                    sales.setDescription(newSales.getDescription());
                    return salesRepository.save(sales);
                }).orElseGet(() -> {
                    newSales.setId(id);
                    return salesRepository.save(newSales);
                });
    }

    public Sales updateSales(Sales updateSales) {
        return salesRepository.save(updateSales);
    }

    public boolean delete(Long id) {
        try {
            salesRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
