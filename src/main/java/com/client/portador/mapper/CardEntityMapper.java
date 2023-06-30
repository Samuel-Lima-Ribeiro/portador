package com.client.portador.mapper;

import com.client.portador.model.Card;
import com.client.portador.repository.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardEntityMapper {
    CardEntity from(Card card);
}
