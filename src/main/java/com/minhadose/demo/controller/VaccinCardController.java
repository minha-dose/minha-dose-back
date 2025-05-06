package com.minhadose.demo.controller;

import com.minhadose.demo.model.VaccinCardModel;
import com.minhadose.demo.service.VaccinCardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccincards")
public class VaccinCardController {

    @Autowired
    private VaccinCardService service;

    @PostMapping
    public ResponseEntity<VaccinCardModel> createCard(@RequestBody VaccinCardModel card) {
        try {
            VaccinCardModel created = service.createCard(card);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<VaccinCardModel> updateCard(@RequestParam Long id, @RequestBody VaccinCardModel card) {
        try {
            VaccinCardModel updated = service.updateById(id, card);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinCardModel> getCardById(@PathVariable Long id) {
        try {
            VaccinCardModel card = service.findById(id);
            return ResponseEntity.ok(card);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<VaccinCardModel>> getAllCards() {
        List<VaccinCardModel> cards = service.listAllCards();
        return cards.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(cards);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}