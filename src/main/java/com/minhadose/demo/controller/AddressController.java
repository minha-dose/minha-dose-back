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

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long addressId) {
        try {
            AddressModel address = addressService.findById(addressId);
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<AddressModel> findByAddressByUserIId(@PathVariable Long userId) {
        try {
            AddressModel address = addressService.findByAddressByUserIId(userId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/ubs/{ubsId}")
    public ResponseEntity<AddressModel> findByAddressByUbsIId(@PathVariable Long ubsId) {
        try {
            AddressModel address = addressService.findByAddressByUbsIId(ubsId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
