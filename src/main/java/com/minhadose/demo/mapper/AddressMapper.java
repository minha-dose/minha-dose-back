package com.minhadose.demo.mapper;

import org.mapstruct.Mapper;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.AddressModel;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressModel toDomain(AddressDto dto);
    AddressDto toDTO(AddressModel model);

}
