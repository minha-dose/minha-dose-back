package com.minhadose.demo.model;

import jakarta.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "appointments")
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateAppointment;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserModel user;

    @OneToOne
    @JoinColumn(name = "ubs_id", referencedColumnName = "ubsId")
    private UbsModel ubs;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Calendar dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UbsModel getUbs() {
        return ubs;
    }

    public void setUbs(UbsModel ubs) {
        this.ubs = ubs;
    }
}
