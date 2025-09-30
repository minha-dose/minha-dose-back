package com.minhadose.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.minhadose.demo.dto.AddressDto;
import com.minhadose.demo.model.AddressModel;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "addressId", ignore = true)
    @Mapping(target = "ubsModel", ignore = true)
    @Mapping(target = "userModel", ignore = true)
    AddressModel toDomain(AddressDto dto);
    AddressDto toDTO(AddressModel model);

}
