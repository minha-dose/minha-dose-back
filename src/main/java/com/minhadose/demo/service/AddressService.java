package com.minhadose.demo.service;

import com.minhadose.demo.model.AddressModel;
import com.minhadose.demo.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressModel findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    public List<AddressModel> listAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteById(Long id){
        AddressModel address = findById(id);
        addressRepository.deleteById(id);
    }

    public AddressModel updateById(Long id, AddressModel updatedAddress){
        AddressModel address = findById(id);
        address.setStreet(updatedAddress.getStreet());
        address.setCity(updatedAddress.getCity());
        address.setCountry(updatedAddress.getCountry());
        address.setZipCode(updatedAddress.getZipCode());
        address.setExtraInfo(updatedAddress.getExtraInfo());
        addressRepository.save(address);
        return address;
    }

    public AddressModel createAddress(AddressModel address) {
        AddressModel newAddress = addressRepository.save(address);
        return newAddress;
    }
}
