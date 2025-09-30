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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@Tag(name = "Vacinas", description = "Operações relacionadas às vacinas")
@RestController
@RequestMapping("/api/vaccins")
public class VaccinController {

    @Autowired
    private VaccinService vaccinService;


    @Operation(summary = "Listar vacinas", description = "Retorna todas as vacinas cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de vacinas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<VaccinModel>> getAllVaccins() {
        List<VaccinModel> vaccins = vaccinService.getAllVaccins();
        return ResponseEntity.ok(vaccins);
    }


    @Operation(summary = "Buscar vacina por ID", description = "Retorna uma vacina pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vacina encontrada"),
        @ApiResponse(responseCode = "404", description = "Vacina não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VaccinModel> getVaccinById(
            @Parameter(description = "ID da vacina", example = "1")
            @PathVariable("id") Long id) {
        return vaccinService.getVaccinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Criar vacina", description = "Cria uma nova vacina.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Vacina criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da vacina")
    })
    @PostMapping
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da vacina a ser criada")
    public ResponseEntity<?> createVaccin(
        @Valid
        @RequestBody VaccinModel vaccin) {
        try {
            VaccinModel createdVaccin = vaccinService.createVaccin(vaccin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVaccin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(summary = "Atualizar vacina", description = "Atualiza uma vacina existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vacina atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vacina não encontrada")
    })
    @PutMapping("/{id}")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados atualizados da vacina")
    public ResponseEntity<?> updateVaccin(
        @Parameter(description = "ID da vacina", example = "1")
        @PathVariable("id") Long id,
        @Valid
        @RequestBody VaccinModel vaccinDetails) {
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


    @Operation(summary = "Deletar vacina", description = "Remove uma vacina pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Vacina removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vacina não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVaccin(
            @Parameter(description = "ID da vacina", example = "1")
            @PathVariable("id") Long id) {
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



    @Operation(summary = "Buscar vacinas por nome", description = "Retorna uma lista de vacinas pelo nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vacinas encontradas")
    })
    @GetMapping("/search/by-name")
    public ResponseEntity<List<VaccinModel>> getVaccinsByName(
            @Parameter(description = "Nome da vacina para busca", example = "BCG")
            @RequestParam String name) {
        List<VaccinModel> vaccins = vaccinService.getVaccinsByName(name);
        return ResponseEntity.ok(vaccins);
    }



    @Operation(summary = "Buscar vacinas por UBS", description = "Retorna uma lista de vacinas de uma UBS específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vacinas encontradas"),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada")
    })
    @GetMapping("/search/by-ubs")
    public ResponseEntity<?> getVaccinsByUbsId(
            @Parameter(description = "ID da UBS", example = "1")
            @RequestParam Long ubsId) {
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