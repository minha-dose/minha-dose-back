package com.minhadose.demo.controller;

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
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressModel address){
        try {
            AddressModel newAddress = addressService.createAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<AddressModel> updateAddress(@RequestParam Long addressId, @RequestBody AddressModel address){
        try{
            AddressModel updatedAddress = addressService.updateById(addressId, address);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@RequestParam Long addressId){
        try{
            AddressModel address = addressService.findById(addressId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAllAddresses(){
        List<AddressModel> addresses = addressService.listAllAddresses();
        if(addresses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
