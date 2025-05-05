package com.minhadose.demo.repository;

import com.minhadose.demo.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Long> {
    
    
    List<ContactModel> findByNomeContainingIgnoreCase(String nome);
    List<ContactModel> findByEmail(String email);
}