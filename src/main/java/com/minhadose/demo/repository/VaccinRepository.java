package com.minhadose.demo.repository;

import com.minhadose.demo.model.VaccinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VaccinRepository extends JpaRepository<VaccinModel, Long> {

    /**
     * Pesquisa vacinas cujo nome contém a string fornecida, ignorando maiúsculas/minúsculas.
     * @param name O nome (ou parte do nome) da vacina a ser pesquisada.
     * @return Uma lista de VaccinModel que correspondem ao critério.
     */
    List<VaccinModel> findByNameContainingIgnoreCase(String name);

    /**
     * Pesquisa vacinas por ID da UBS.
     * @param ubsId O ID da UBS.
     * @return Uma lista de VaccinModel associadas à UBS com o ID fornecido.
     */
    List<VaccinModel> findByUbsUbsId(Long ubsId);

    /**
     * @return Uma lista de VaccinModel onde o campo 'isMandatory' é true.
     */
    List<VaccinModel> findByIsMandatoryTrue();
}