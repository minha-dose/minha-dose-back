package com.minhadose.demo.dto;

import javax.management.relation.Role;

public record CreateUserDTO(
        String name,
        String password,
        String email,
        Integer age,
        Role role) {
}