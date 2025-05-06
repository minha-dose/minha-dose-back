package com.minhadose.demo.dto;

public record CreateUbsDTO(
        String name,
        ContactDto contact,
        AddressDto address
) {}