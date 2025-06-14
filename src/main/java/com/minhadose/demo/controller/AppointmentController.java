package com.minhadose.demo.controller;

import com.minhadose.demo.model.AppointmentModel;
import com.minhadose.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentModel create(@RequestBody AppointmentModel appointment) {
        return appointmentService.create(appointment);
    }

    @GetMapping
    public List<AppointmentModel> listAll() {
        return appointmentService.listAll();
    }

    @GetMapping("/{id}")
    public AppointmentModel getById(@PathVariable Long id) {
        return appointmentService.getById(id);
    }

    @PutMapping("/{id}")
    public AppointmentModel update(@PathVariable Long id, @RequestBody AppointmentModel updated) {
        return appointmentService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
