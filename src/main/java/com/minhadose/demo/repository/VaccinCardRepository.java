package com.minhadose.demo.repository;

import com.minhadose.demo.model.VaccinCardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinCardRepository extends JpaRepository<VaccinCardModel, Long> {
}
