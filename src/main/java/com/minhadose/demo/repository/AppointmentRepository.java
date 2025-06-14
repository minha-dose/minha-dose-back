package com.minhadose.demo.repository;
import com.minhadose.demo.model.AppointmentModel;
import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    AppointmentModel findByUser(UserModel user);
    AppointmentModel findByUbs(UbsModel ubs);
}
