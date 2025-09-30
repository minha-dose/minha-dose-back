package com.minhadose.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.minhadose.demo.dto.ContactDto;
import com.minhadose.demo.model.ContactModel;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    ContactModel toDomain(ContactDto dto);
    ContactDto toDTO(ContactModel model);
}
