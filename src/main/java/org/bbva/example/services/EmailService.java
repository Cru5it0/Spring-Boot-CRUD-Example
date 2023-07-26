package org.bbva.example.services;

import org.bbva.example.errors.ItemNotFoundException;
import org.bbva.example.model.Email;
import org.bbva.example.reposiroty.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    public ArrayList<Email> getAllEmail() {
        return (ArrayList<Email>) emailRepository.findAll();
    }

    public Email getIdEmail(Long id) {
        return emailRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public Email update(Email newEmail, Long id) {
        return emailRepository.findById(id)
                .map(email -> {
                    email.setEmail(newEmail.getEmail());
                    email.setPassword(newEmail.getPassword());
                    return emailRepository.save(email);
                }).orElseGet(() -> {
                    newEmail.setId(id);
                    return emailRepository.save(newEmail);
                });
    }

    public boolean delete(Long id) {
        try{
            emailRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
