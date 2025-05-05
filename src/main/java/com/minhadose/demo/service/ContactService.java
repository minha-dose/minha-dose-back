package com.minhadose.demo.service;

import com.minhadose.demo.model.ContactModel;
import com.minhadose.demo.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Método para listar todos os contatos
    public List<ContactModel> getAllContacts() {
        return contactRepository.findAll();
    }

    // Método para buscar um contato por ID
    public Optional<ContactModel> getContactById(Long id) {
        return Optional.ofNullable(contactRepository.findById(id).orElse(null));
    }

    // Método para criar um novo contato
    public ContactModel createContact(ContactModel contact) {
        return contactRepository.save(contact);
    }

    // Método para atualizar um contato existente
    public ContactModel updateContact(Long id, ContactModel contact) {
        return contactRepository.findById(id).map(existingContact -> {
            updateExistingContact(existingContact, contact);
            return contactRepository.save(existingContact);
        }).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado com o ID: " + id));
    }

    // Método para deletar um contato por ID
    public void deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Contato não encontrado com o ID: " + id);
        }
    }
}