package com.minhadose.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.minhadose.demo.dto.CreateUbsDTO;
import com.minhadose.demo.model.UbsModel;

@Mapper(componentModel = "spring")
public interface UbsMapper {
    
    @Mapping(target = "ubsId", ignore = true)
    UbsModel toUbsModel(CreateUbsDTO dto);
    CreateUbsDTO toCreateUbsDTO(UbsModel ubsModel);
}