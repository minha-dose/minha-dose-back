package com.minhadose.demo.dto.request;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.Role;

public record UserRequest(
        String name,
        String password,
        String email,
        Integer age,
        Role role,
        AddressDto address) {
}