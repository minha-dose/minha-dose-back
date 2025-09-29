package com.minhadose.demo.repository;

import com.minhadose.demo.model.UbsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UbsRepository extends JpaRepository<UbsModel, Long> {
    List<UbsModel> findByNameContainingIgnoreCase(String name);

}