package com.minhadose.demo.repository;

import com.minhadose.demo.model.AddressModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    Optional<AddressModel> findByUbsId(Long ubsId);

}
