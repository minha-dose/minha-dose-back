package com.minhadose.demo.dto;

import com.minhadose.demo.model.Role;

public record CreateUserDTO(
        String name,
        String password,
        String email,
        Integer age,
        Role role,
        AddressDto address) {
}