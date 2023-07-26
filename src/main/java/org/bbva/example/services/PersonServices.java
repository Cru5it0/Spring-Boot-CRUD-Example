package org.bbva.example.services;

import org.bbva.example.errors.ItemNotFoundException;
import org.bbva.example.model.Person;
import org.bbva.example.reposiroty.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

    public ArrayList<Person> getAllPersons() {
        return (ArrayList<Person>) personRepository.findAll();
    }

    public Person getIdPerson(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person newPerson, Long id) {
        return personRepository.findById(id)
                .map(item -> {
                    item.setName(newPerson.getName());
                    item.setAge(newPerson.getAge());
                    return personRepository.save(item);
                }).orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    public boolean deletePerson(Long id) {
        try{
            personRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
