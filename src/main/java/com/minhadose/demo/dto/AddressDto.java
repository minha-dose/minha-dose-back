package com.minhadose.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {
    
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String extraInfo;
}
