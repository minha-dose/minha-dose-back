package com.minhadose.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhadose.demo.dto.MandatoryVaccinDTO;
import com.minhadose.demo.mapper.VaccinMapper;
import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.model.VaccinModel;
import com.minhadose.demo.repository.UbsRepository;
import com.minhadose.demo.repository.VaccinRepository;


@Service
public class VaccinService {

    @Autowired
    private VaccinRepository vaccinRepository;

    @Autowired
    private UbsRepository ubsRepository;

    @Transactional(readOnly = true)
    public List<VaccinModel> getAllVaccins() {
        return vaccinRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<VaccinModel> getVaccinById(Long id) {
        return vaccinRepository.findById(id);
    }

    @Transactional
    public VaccinModel createVaccin(VaccinModel vaccin) {
        if (vaccin.getUbs() == null || vaccin.getUbs().getUbsId() == null) {
            throw new IllegalArgumentException("A UBS e o ID da UBS são obrigatórios para cadastrar a vacina.");
        }

        UbsModel ubs = ubsRepository.findById(vaccin.getUbs().getUbsId())
                .orElseThrow(() -> new RuntimeException("UBS não encontrada com ID: " + vaccin.getUbs().getUbsId()));
        vaccin.setUbs(ubs);

        // 1. VALIDAÇÃO ADICIONADA AQUI
        validateMandatoryFields(vaccin);

        return vaccinRepository.save(vaccin);
    }

    @Transactional
    public VaccinModel updateVaccin(Long id, VaccinModel updatedVaccinDetails) {
        VaccinModel existingVaccin = vaccinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada com ID: " + id));
        
        // Atualiza os campos existentes
        existingVaccin.setName(updatedVaccinDetails.getName());
        existingVaccin.setManufacturer(updatedVaccinDetails.getManufacturer());
        existingVaccin.setBatch(updatedVaccinDetails.getBatch());
        existingVaccin.setExpiration(updatedVaccinDetails.getExpiration());
        existingVaccin.setOriginalQuantity(updatedVaccinDetails.getOriginalQuantity());
        existingVaccin.setCurrentQuantity(updatedVaccinDetails.getCurrentQuantity());

        // 2. ATUALIZAÇÃO DOS NOVOS CAMPOS
        existingVaccin.setMandatory(updatedVaccinDetails.isMandatory());
        existingVaccin.setMandatoryAge(updatedVaccinDetails.getMandatoryAge());

        if (updatedVaccinDetails.getUbs() != null && updatedVaccinDetails.getUbs().getUbsId() != null) {
            UbsModel ubs = ubsRepository.findById(updatedVaccinDetails.getUbs().getUbsId())
                    .orElseThrow(() -> new RuntimeException("UBS não encontrada com ID: " + updatedVaccinDetails.getUbs().getUbsId()));
            existingVaccin.setUbs(ubs);
        } else if (updatedVaccinDetails.getUbs() != null && updatedVaccinDetails.getUbs().getUbsId() == null) {
            throw new IllegalArgumentException("Para atualizar a UBS, o ID da UBS (ubsId) deve ser fornecido dentro do objeto ubs.");
        }

        // 3. VALIDAÇÃO ADICIONADA AQUI
        validateMandatoryFields(existingVaccin);

        return vaccinRepository.save(existingVaccin);
    }

    @Transactional
    public void deleteVaccin(Long id) {
        if (!vaccinRepository.existsById(id)) {
            throw new RuntimeException("Vacina não encontrada com ID: " + id + " para exclusão.");
        }
        vaccinRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<VaccinModel> getVaccinsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return vaccinRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<VaccinModel> getVaccinsByUbsId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID da UBS não pode ser nulo.");
        }
        if (!ubsRepository.existsById(id)) {
            throw new RuntimeException("UBS não encontrada com ID: " + id);
        }
        return vaccinRepository.findByUbsUbsId(id);
    }

   
    /**
     * Busca todas as vacinas marcadas como obrigatórias e as converte para DTO.
     * @return Uma lista de MandatoryVaccinDTO.
     */
    @Transactional(readOnly = true)
    public List<MandatoryVaccinDTO> getMandatoryVaccins() {
        List<VaccinModel> vaccins = vaccinRepository.findByIsMandatoryTrue();
        return VaccinMapper.toMandatoryDTOList(vaccins);
    }
    
    /**
     * Valida as regras de negócio para campos de obrigatoriedade da vacina.
     * @param vaccin O objeto VaccinModel a ser validado.
     */
    private void validateMandatoryFields(VaccinModel vaccin) {
        // Regra 1: Se for obrigatória, a idade deve ser informada.
        if (vaccin.isMandatory() && vaccin.getMandatoryAge() == null) {
            throw new IllegalArgumentException("Para uma vacina obrigatória, a idade (mandatoryAge) deve ser informada.");
        }
        // Regra 2: Se NÃO for obrigatória, a idade deve ser nula para manter a consistência.
        if (!vaccin.isMandatory()) {
            vaccin.setMandatoryAge(null);
        }
    }
}