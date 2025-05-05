package com.minhadose.demo.model;

import jakarta.persistence.*;

@Entity
public class VaccinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccinId;

    private String name;
    private String manufacturer;
    private String batch;
    private String expiration;
    private int originalQuantity;
    private int currentQuantity;

    public VaccinModel() {}

    public VaccinModel(Long vaccinId, String name, String manufacturer, String batch, String expiration,
                       int originalQuantity, int currentQuantity) {
        this.vaccinId = vaccinId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.batch = batch;
        this.expiration = expiration;
        this.originalQuantity = originalQuantity;
        this.currentQuantity = currentQuantity;
    }

    public Long getVaccinId() {
        return vaccinId;
    }

    public void setVaccinId(Long vaccinId) {
        this.vaccinId = vaccinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(int originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}
