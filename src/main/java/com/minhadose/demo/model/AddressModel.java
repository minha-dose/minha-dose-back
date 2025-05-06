package com.minhadose.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String extraInfo;

    @OneToOne(mappedBy = "address", optional = true)
	@JsonBackReference(value = "ubs-address")
    private UbsModel ubsModel;

    @OneToOne(mappedBy = "address", optional = true)
    @JsonBackReference(value = "user-address")
    private UserModel userModel;

}
