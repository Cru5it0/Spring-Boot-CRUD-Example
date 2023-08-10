package org.bbva.example.controller;

import org.bbva.example.model.Sales;
import org.bbva.example.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class SalesController {

    @Autowired(required = false)
    SalesService salesService;

    @GetMapping("/sales")
    public ArrayList<Sales> getAllSales() {
        return (ArrayList<Sales>) salesService.getAllSales();
    }

    @PostMapping("/sales")
    public Sales addSales(@Valid @RequestBody Sales newSales) {
        return this.salesService.save(newSales);
    }

    @GetMapping("/sales/{id}")
    public Sales getIdSales(@PathVariable("id") Long id) {
        return salesService.getIdSales(id);
    }

    @PutMapping("/sales/{id}")
    public Sales updateSales(@Valid @RequestBody Sales newSales, @PathVariable Long id) {
        return this.salesService.update(newSales, id);
    }

    @DeleteMapping("/sales/{id}")
    public String deletSales(@PathVariable("id") Long id) {
        boolean success = this.salesService.delete(id);

        if(success) {
            return "Successful removal";
        } else {
            return "Deletion was not completed";
        }
    }

}
