package com.minhadose.demo.repository;

import com.minhadose.demo.model.UbsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbsRepository extends JpaRepository<UbsModel, Long> {
}