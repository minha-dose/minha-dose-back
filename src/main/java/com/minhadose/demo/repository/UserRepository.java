package com.minhadose.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhadose.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    
}
