package com.client.portador.mapper;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.model.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card from(CardRequest cardRequest);
}
