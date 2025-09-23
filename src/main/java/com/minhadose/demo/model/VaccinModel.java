package com.minhadose.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter; 
import lombok.Setter; 
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor; 

@Entity
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class VaccinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @NotBlank(message = "O fabricante não pode estar em branco")
    private String manufacturer;

    @NotBlank(message = "O lote não pode estar em branco")
    private String batch;

    @NotNull(message = "A data de validade não pode ser nula")
    @FutureOrPresent(message = "A data de validade deve ser no presente ou futuro")
    private LocalDate expiration;

    @NotNull(message = "A quantidade original não pode ser nula")
    @PositiveOrZero(message = "A quantidade original deve ser positiva ou zero")
    private Integer originalQuantity;

    @NotNull(message = "A quantidade atual não pode ser nula")
    @PositiveOrZero(message = "A quantidade atual deve ser positiva ou zero")
    private Integer currentQuantity;

    @ManyToOne
    @JoinColumn(name = "ubs_id", nullable = false)
    @NotNull(message = "A UBS não pode ser nula")
    private UbsModel ubs;


}