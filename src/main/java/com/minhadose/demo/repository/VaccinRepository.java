package com.minhadose.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhadose.demo.model.VaccinModel;

@Repository
public interface VaccinRepository extends JpaRepository<VaccinModel, Long> {
    Optional<VaccinModel> findByName(String name);
}
