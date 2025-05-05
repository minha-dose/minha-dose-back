package com.minhadose.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhadose.demo.model.VaccinModel;
import com.minhadose.demo.service.VaccinService;

@RestController
@RequestMapping("/api/vaccins")
public class VaccinController {

    @Autowired
    private VaccinService vaccinService;

    @GetMapping
    public List<VaccinModel> getAllVaccins() {
        return vaccinService.getAllVaccins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinModel> getVaccinById(@PathVariable Long id) {
        return vaccinService.getVaccinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VaccinModel> createVaccin(@RequestBody VaccinModel vaccin) {
        return ResponseEntity.ok(vaccinService.createVaccin(vaccin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinModel> updateVaccin(@PathVariable Long id, @RequestBody VaccinModel vaccin) {
        return ResponseEntity.ok(vaccinService.updateVaccin(id, vaccin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccin(@PathVariable Long id) {
        vaccinService.deleteVaccin(id);
        return ResponseEntity.noContent().build();
    }
}
