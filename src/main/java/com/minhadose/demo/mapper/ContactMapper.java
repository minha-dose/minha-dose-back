
package com.minhadose.demo.mapper;

import com.minhadose.demo.dto.ContactDto;
import com.minhadose.demo.model.ContactModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ubs", ignore = true)
    ContactModel toModel(ContactDto dto);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "ubsId", source = "ubs.ubsId")
    ContactDto toDto(ContactModel model);
}
