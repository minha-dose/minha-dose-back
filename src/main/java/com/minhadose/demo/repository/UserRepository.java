package com.minhadose.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhadose.demo.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
    List<UserModel> findByAddress_City(String city);
    List<UserModel> findByAddress_ZipCode(String cep);
}
