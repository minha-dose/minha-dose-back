package com.minhadose.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String countryCode;
    private String stateCode;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}