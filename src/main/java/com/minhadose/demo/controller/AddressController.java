package com.minhadose.demo.controller;

import com.minhadose.demo.model.AddressModel;
import com.minhadose.demo.service.AddressService;
import jakarta.persistence.EntityNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")

@Tag(name = "Endereços", description = "Operações relacionadas a endereços")
public class AddressController {

    @Autowired
    AddressService addressService;


    @Operation(summary = "Buscar endereço por ID", description = "Retorna um endereço pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressModel> getAddressById(
        @Parameter(description = "ID do endereço", example = "1")
        @PathVariable("addressId") Long addressId) {
        try {
            AddressModel address = addressService.findById(addressId);
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @Operation(summary = "Listar todos os endereços", description = "Retorna uma lista de todos os endereços cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado")
    })
    @GetMapping
    public ResponseEntity<List<AddressModel>> getAllAddresses() {
        List<AddressModel> addresses = addressService.listAllAddresses();
        if (addresses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }


    @Operation(summary = "Buscar endereço por ID do usuário", description = "Retorna o endereço associado a um usuário específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o usuário informado")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<AddressModel> findByAddressByUserIId(
        @Parameter(description = "ID do usuário", example = "1")
        @PathVariable("userId") Long userId) {
        try {
            AddressModel address = addressService.findByAddressByUserIId(userId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @Operation(summary = "Buscar endereço por ID da UBS", description = "Retorna o endereço associado a uma UBS específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado para a UBS informada")
    })
    @GetMapping("/ubs/{ubsId}")
    public ResponseEntity<AddressModel> findByAddressByUbsIId(
        @Parameter(description = "ID da UBS", example = "1")
        @PathVariable("ubsId") Long ubsId) {
        try {
            AddressModel address = addressService.findByAddressByUbsIId(ubsId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
