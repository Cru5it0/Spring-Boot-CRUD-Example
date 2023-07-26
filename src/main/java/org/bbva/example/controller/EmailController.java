package org.bbva.example.controller;

import org.bbva.example.model.Email;
import org.bbva.example.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/email")
    public ArrayList<Email> getAllEmail() {
        return (ArrayList<Email>) emailService.getAllEmail();
    }

    @PostMapping("/email")
    public Email addEmail(@Valid @RequestBody Email newEmail) {
        return this.emailService.save(newEmail);
    }

    @GetMapping("/email/{id}")
    public Email getIdEmail(@PathVariable("id") Long id) {
        return emailService.getIdEmail(id);
    }

    @PutMapping("/email/{id}")
    public Email updateEmail(@Valid @RequestBody Email newEmpail, @PathVariable Long id) {
        return this.emailService.update(newEmpail, id);
    }

    @DeleteMapping("/email/{id}")
    public String deleteEmail(@PathVariable("id") Long id) {
        boolean success = this.emailService.delete(id);

        if(success) {
            return "Successful removal";
        } else {
            return "Deletion was not completed";
        }

    }

}
