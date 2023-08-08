package org.bbva.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bbva.example.model.Person;
import org.bbva.example.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Api(tags = "people-controller", description = "CRUD of people in Data Base from application")
public class PersonController {

    @Autowired
    PersonServices personServices;

    @GetMapping(value = "/person")
    @ApiOperation("Get all people")
    List<Person> all() {
        return personServices.getAllPersons();
    }

    @PostMapping(value = "/person")
    @ApiOperation("Add a new person")
    public Person addPerson(@Valid @RequestBody Person newPerson) {
        return this.personServices.save(newPerson);
    }

    @GetMapping(value = "/person/{id}")
    @ApiOperation("Find person for each id")
    public Person getIdPerson(@PathVariable("id") Long id) {
        return personServices.getIdPerson(id);
    }

    @PutMapping(value = "/person/{id}")
    @ApiOperation("Update the person info")
    public Person updatePerson(@Valid @RequestBody Person newPerson, @PathVariable Long id) {
        return this.personServices.update(newPerson, id);
    }

    @DeleteMapping(value = "/person/{id}")
    @ApiOperation("Delete person from the DataBase")
    public String delete(@PathVariable("id") Long id) {
        boolean success = this.personServices.deletePerson(id);

        if(success) {
            return "Successful removal";
        } else {
            return "Deletion was not completed";
        }
    }

}
