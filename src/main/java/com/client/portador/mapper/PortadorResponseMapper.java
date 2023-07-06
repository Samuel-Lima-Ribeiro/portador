package com.client.portador.mapper;

import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.repository.entity.PortadorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortadorResponseMapper {
    @Mapping(source = "id", target = "cardHolderId")
    PortadorResponse from(PortadorEntity portadorEntity);
}
