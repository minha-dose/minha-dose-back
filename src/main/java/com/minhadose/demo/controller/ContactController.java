package com.minhadose.demo.controller;

import com.minhadose.demo.model.ContactModel;
import com.minhadose.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactModel>> getAllContacts() {
        List<ContactModel> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactModel> getContactById(@PathVariable Long id) {
        return contactService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactModel> createContact(@RequestBody ContactModel contact) {
        ContactModel newContact = contactService.createContact(contact);
        return ResponseEntity.ok(newContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactModel> updateContact(@PathVariable Long id, @RequestBody ContactModel contact) {
        try {
            ContactModel updatedContact = contactService.updateContact(id, contact);
            return ResponseEntity.ok(updatedContact);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
