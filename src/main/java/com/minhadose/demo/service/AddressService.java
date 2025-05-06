package com.minhadose.demo.service;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.AddressModel;
import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressModel findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    public List<AddressModel> listAllAddresses(){
        return addressRepository.findAll();
    }
    public AddressModel findByAddressByUserIId(Long userId) {
        return addressRepository.findByUserModel_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Address for user not found"));
    }
    public AddressModel findByAddressByUbsIId(Long ubsId) {
        return addressRepository.findByUbsModel_UbsId(ubsId)
                .orElseThrow(() -> new EntityNotFoundException("Address for user not found"));
    }                    
}
