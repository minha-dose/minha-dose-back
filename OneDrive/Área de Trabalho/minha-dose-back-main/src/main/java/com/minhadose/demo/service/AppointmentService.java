package com.minhadose.demo.service;

import com.minhadose.demo.model.AppointmentModel;
import com.minhadose.demo.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentModel create(AppointmentModel appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<AppointmentModel> listAll() {
        return appointmentRepository.findAll();
    }

    public AppointmentModel getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

    public AppointmentModel update(Long id, AppointmentModel updatedAppointment) {
        AppointmentModel existing = getById(id);
        existing.setDateAppointment(updatedAppointment.getDateAppointment());
        existing.setUser(updatedAppointment.getUser());
        existing.setUbs(updatedAppointment.getUbs());
        return appointmentRepository.save(existing);
    }
}
