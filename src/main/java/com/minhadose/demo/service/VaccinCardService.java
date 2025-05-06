package com.minhadose.demo.service;

import com.minhadose.demo.model.VaccinCardModel;
import com.minhadose.demo.repository.VaccinCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinCardService {

    @Autowired
    private VaccinCardRepository repository;

    public VaccinCardModel createCard(VaccinCardModel card) {
        return repository.save(card);
    }

    public VaccinCardModel updateById(Long id, VaccinCardModel card) {
        if (repository.existsById(id)) {
            card.setVaccinCardId(id);
            return repository.save(card);
        } else {
            throw new EntityNotFoundException("VaccinCard not found with id: " + id);
        }
    }

    public VaccinCardModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VaccinCard not found with id: " + id));
    }

    public List<VaccinCardModel> listAllCards() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("VaccinCard not found with id: " + id);
        }
    }
}
