package com.minhadose.demo.repository;

import com.minhadose.demo.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {
    List<ContactModel> findByUbs_UbsId(Long ubsId);
    List<ContactModel> findByUser_UserId(Long userId);
}
