package com.minhadose.demo.controller;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.AddressModel;
import com.minhadose.demo.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressDto addressDto) {
        try {
            AddressModel newAddress = addressService.createAddress(addressDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long addressId,
            @RequestBody AddressDto addressDto) {
        try {
            AddressModel updatedAddress = addressService.updateById(addressId, addressDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        try {
            AddressModel address = addressService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAllAddresses() {
        List<AddressModel> addresses = addressService.listAllAddresses();
        if (addresses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /* @Bruno - Erro a ser analisado
    @GetMapping("/ubs/{ubsId}")
    public ResponseEntity<AddressModel> getAddressByUbsId(@PathVariable Long ubsId) {
        try {
            AddressModel address = addressService.findByUbsId(ubsId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    */
}
