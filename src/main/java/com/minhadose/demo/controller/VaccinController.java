package com.minhadose.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhadose.demo.model.VaccinModel;
import com.minhadose.demo.service.VaccinService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vaccins")
public class VaccinController {

    @Autowired
    private VaccinService vaccinService;

    @GetMapping
    public ResponseEntity<List<VaccinModel>> getAllVaccins() {
        List<VaccinModel> vaccins = vaccinService.getAllVaccins();
        return ResponseEntity.ok(vaccins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinModel> getVaccinById(@PathVariable Long id) {
        return vaccinService.getVaccinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> createVaccin(@Valid @RequestBody VaccinModel vaccin) {
        try {
            VaccinModel createdVaccin = vaccinService.createVaccin(vaccin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVaccin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVaccin(@PathVariable Long id, @Valid @RequestBody VaccinModel vaccinDetails) {
        try {
            VaccinModel updatedVaccin = vaccinService.updateVaccin(id, vaccinDetails);
            return ResponseEntity.ok(updatedVaccin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) { 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVaccin(@PathVariable Long id) {
        try {
            vaccinService.deleteVaccin(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) { 
             if (e.getMessage().contains("não encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar vacina.");
        }
    }


    @GetMapping("/search/by-name")
    public ResponseEntity<List<VaccinModel>> getVaccinsByName(@RequestParam String name) {
        List<VaccinModel> vaccins = vaccinService.getVaccinsByName(name);
        if (vaccins.isEmpty() && (name != null && !name.isEmpty())) {

            return ResponseEntity.ok(vaccins);
        }
        return ResponseEntity.ok(vaccins);
    }


    @GetMapping("/search/by-ubs")
    public ResponseEntity<?> getVaccinsByUbsId(@RequestParam Long ubsId) {
         try {
            List<VaccinModel> vaccins = vaccinService.getVaccinsByUbsId(ubsId);

            return ResponseEntity.ok(vaccins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
             if (e.getMessage().contains("UBS não encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar vacinas por UBS.");
        }
    }
}