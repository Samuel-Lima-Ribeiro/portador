package com.client.portador.service;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.response.CardResponse;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.PortadorEntity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PortadorRepository portadorRepository;

    public CardResponse criarCartao(UUID idPortador, CardRequest cardRequest) {
        System.out.println("Id " + idPortador);
        System.out.println("cardRequest " + cardRequest);
        checarPortador(idPortador);
        return null;
    }

    // realmente preciso desse método?
    public BigDecimal checarPortador(UUID idPortador) {
        final PortadorEntity portadorEntity = portadorRepository.findById(idPortador).orElseThrow(() ->
                new CardHolderNotFoundException("Portador do id %s não encontrado".formatted(idPortador)));
        return portadorEntity.getLimit();
    }
}
