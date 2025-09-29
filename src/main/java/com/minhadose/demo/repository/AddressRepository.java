package com.minhadose.demo.repository;

import com.minhadose.demo.model.AddressModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    
    Optional<AddressModel> findByUserModel_UserId(Long userId);
    Optional<AddressModel> findByUbsModel_UbsId(Long ubsId);

}
