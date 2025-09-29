package com.minhadose.demo.service;

import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.repository.UbsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbsService {
    @Autowired
    private UbsRepository ubsRepository;
    
    public UbsModel createUbs(UbsModel ubs) {
        return ubsRepository.save(ubs);
    }
    
    public UbsModel updateById(Long id, UbsModel ubs) {
        if (ubsRepository.existsById(id)) {
            ubs.setUbsId(id);
            return ubsRepository.save(ubs);
        } else {
            throw new EntityNotFoundException("UBS not found with id: " + id);
        }
    }
    
    public UbsModel findById(Long id) {
        return ubsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("UBS not found with id: " + id));
    }
    
    public List<UbsModel> listAllUbs() {
        return ubsRepository.findAll();
    }
    
    public void deleteById(Long id) {
        if (ubsRepository.existsById(id)) {
            ubsRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("UBS not found with id: " + id);
        }
    }
    public List<UbsModel> findByName(String name) {
        return ubsRepository.findByNameContainingIgnoreCase(name);
    }
    
}