package com.minhadose.demo.controller;

import com.minhadose.demo.dto.ContactDto;
import com.minhadose.demo.mapper.ContactMapper;
import com.minhadose.demo.model.ContactModel;
import com.minhadose.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    @GetMapping
    public List<ContactDto> findAll() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findById(@PathVariable Long id) {
        Optional<ContactModel> contact = contactRepository.findById(id);
        return contact.map(c -> ResponseEntity.ok(contactMapper.toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContactDto create(@RequestBody ContactDto dto) {
        ContactModel model = contactMapper.toModel(dto);
        return contactMapper.toDto(contactRepository.save(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> update(@PathVariable Long id, @RequestBody ContactDto dto) {
        Optional<ContactModel> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            ContactModel model = contactMapper.toModel(dto);
            model.setId(id);
            return ResponseEntity.ok(contactMapper.toDto(contactRepository.save(model)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-ubs/{ubsId}")
    public List<ContactDto> findByUbsId(@PathVariable Long ubsId) {
        return contactRepository.findByUbs_UbsId(ubsId).stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-user/{userId}")
    public List<ContactDto> findByUserId(@PathVariable Long userId) {
        return contactRepository.findByUser_UserId(userId).stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }
}
