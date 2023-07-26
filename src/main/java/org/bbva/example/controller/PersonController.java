package org.bbva.example.controller;

import org.bbva.example.model.Person;
import org.bbva.example.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonServices personServices;

    @GetMapping("/person")
    List<Person> all() {
        return personServices.getAllPersons();
    }

    @PostMapping("/person")
    public Person addPerson(@Valid @RequestBody Person newPerson) {
        return this.personServices.save(newPerson);
    }

    @GetMapping("/person/{id}")
    public Person getIdPerson(@PathVariable("id") Long id) {
        return personServices.getIdPerson(id);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@Valid @RequestBody Person newPerson, @PathVariable Long id) {
        return this.personServices.update(newPerson, id);
    }

    @DeleteMapping("/person/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean success = this.personServices.deletePerson(id);

        if(success) {
            return "Successful removal";
        } else {
            return "Deletion was not completed";
        }
    }

}
