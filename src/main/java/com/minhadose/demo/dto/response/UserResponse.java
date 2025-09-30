package com.minhadose.demo.dto.response;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.Role;

public record UserResponse(
        Long userId,
        String name,
        String email,
        Integer age,
        Role role,
        AddressDto address) {
}