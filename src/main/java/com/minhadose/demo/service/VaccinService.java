package com.minhadose.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhadose.demo.model.VaccinModel;
import com.minhadose.demo.repository.VaccinRepository;

@Service
public class VaccinService {

    @Autowired
    private VaccinRepository vaccinRepository;

    public List<VaccinModel> getAllVaccins() {
        return vaccinRepository.findAll();
    }

    public Optional<VaccinModel> getVaccinById(Long id) {
        return vaccinRepository.findById(id);
    }

    public VaccinModel createVaccin(VaccinModel vaccin) {
        return vaccinRepository.save(vaccin);
    }

    public void deleteVaccin(Long id) {
        vaccinRepository.deleteById(id);
    }

    public VaccinModel updateVaccin(Long id, VaccinModel updatedVaccin) {
        return vaccinRepository.findById(id).map(v -> {
            v.setName(updatedVaccin.getName());
            v.setManufacturer(updatedVaccin.getManufacturer());
            v.setBatch(updatedVaccin.getBatch());
            v.setExpiration(updatedVaccin.getExpiration());
            v.setOriginalQuantity(updatedVaccin.getOriginalQuantity());
            v.setCurrentQuantity(updatedVaccin.getCurrentQuantity());
            return vaccinRepository.save(v);
        }).orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }
}
