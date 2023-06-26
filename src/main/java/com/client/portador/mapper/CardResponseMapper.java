package com.client.portador.mapper;

import com.client.portador.controller.response.CardResponse;
import com.client.portador.repository.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardResponseMapper {
    CardResponse from(CardEntity cardEntity);
}
