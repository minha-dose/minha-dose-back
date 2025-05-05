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
    
    @Autowired
    private UbsService ubsService;

    @Autowired
    private UserService userService;

    public AddressModel findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    public List<AddressModel> listAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteById(Long id){
        addressRepository.deleteById(id);
    }

    public AddressModel updateById(Long id, AddressDto addressDto) {
        AddressModel address = findById(id);
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        address.setExtraInfo(addressDto.getExtraInfo());
    
        if (addressDto.getUbsId() != null) {
            address.setUbsModel(ubsService.findById(addressDto.getUbsId()));
        }
    
        /* if (addressDto.getUserId() != null) {
            address.setUserModel(userService.findById(addressDto.getUserId()));
        } */
    
        return addressRepository.save(address);
    }
    

    public AddressModel createAddress(AddressDto addressDTO) {
        AddressModel newAddress = new AddressModel();
        newAddress.setStreet(addressDTO.getStreet());
        newAddress.setCity(addressDTO.getCity());
        newAddress.setState(addressDTO.getState());
        newAddress.setCountry(addressDTO.getCountry());
        newAddress.setZipCode(addressDTO.getZipCode());
        newAddress.setExtraInfo(addressDTO.getExtraInfo());

        if (addressDTO.getUbsId() != null) {
            UbsModel ubs = ubsService.findById(addressDTO.getUbsId()); 
            newAddress.setUbsModel(ubs);
        }

        /* if (addressDTO.getUserId() != null) {
            UserModel user = userService.findById(addressDTO.getUserId()); 
            newAddress.setUserModel(user);
        } */

        return addressRepository.save(newAddress);
    }

    /* @Bruno-Guilherme - Erro a ser analisado.
    public AddressModel findByUbsId(Long ubsId) {
        return addressRepository.findByUbsModel_UbsId(ubsId)
                .orElseThrow(() -> new EntityNotFoundException("Address for ubs not found"));
    }
    */
                
}
