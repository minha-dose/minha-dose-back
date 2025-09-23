package com.minhadose.demo.dto;

public class MandatoryVaccinDTO {

    private String name;
    private int mandatoryAge;

    public MandatoryVaccinDTO() {
    }

    public MandatoryVaccinDTO(String name, int mandatoryAge) {
        this.name = name;
        this.mandatoryAge = mandatoryAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMandatoryAge() {
        return mandatoryAge;
    }

    public void setMandatoryAge(int mandatoryAge) {
        this.mandatoryAge = mandatoryAge;
    }
}