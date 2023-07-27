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
@RequestMapping("person")
@Api(tags = "People Controller", description = "CRUD of people in Data Base from application")
public class PersonController {

    @Autowired
    PersonServices personServices;

    @GetMapping()
    @ApiOperation("Get all people")
    List<Person> all() {
        return personServices.getAllPersons();
    }

    @PostMapping()
    @ApiOperation("Add a new person")
    public Person addPerson(@Valid @RequestBody Person newPerson) {
        return this.personServices.save(newPerson);
    }

    @GetMapping(path = "{id}")
    @ApiOperation("Find person for each id")
    public Person getIdPerson(@PathVariable("id") Long id) {
        return personServices.getIdPerson(id);
    }

    @PutMapping(path = "{id}")
    @ApiOperation("Update the person info")
    public Person updatePerson(@Valid @RequestBody Person newPerson, @PathVariable Long id) {
        return this.personServices.update(newPerson, id);
    }

    @DeleteMapping(path = "{id}")
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
