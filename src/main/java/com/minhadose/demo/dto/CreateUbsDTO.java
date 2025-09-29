package com.minhadose.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUbsDTO {
    private String name;
    private ContactDto contact;
    private AddressDto address;
}