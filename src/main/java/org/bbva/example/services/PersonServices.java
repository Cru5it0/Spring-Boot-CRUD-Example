package org.bbva.example.services;

import org.bbva.example.reposiroty.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

}
