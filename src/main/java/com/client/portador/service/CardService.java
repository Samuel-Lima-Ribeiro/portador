package com.client.portador.service;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.response.CardResponse;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.repository.CardRepository;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.CardEntity;
import com.client.portador.repository.entity.PortadorEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PortadorRepository portadorRepository;
    private final CardRepository cardRepository;

    public CardResponse criarCartao(UUID idPortador, CardRequest cardRequest) {
        // aq
        System.out.println("Id " + idPortador);
        // aq
        System.out.println("cardRequest " + cardRequest);
        final BigDecimal limitePortador = checarPortador(idPortador);
        // aq
        System.out.println("limite " + limitePortador);
        //aq
        calcularLimiteUsado(idPortador);
        return null;
    }

    public BigDecimal checarPortador(UUID idPortador) {
        final PortadorEntity portadorEntity = portadorRepository.findById(idPortador).orElseThrow(() ->
                new CardHolderNotFoundException("Portador do id %s não encontrado".formatted(idPortador)));
        return portadorEntity.getLimit();
    }

    // aq
    // mudar o nome para verificarLimteUsado, ai já lanço a exceção que o limite ultrapassa
    // tirar o return, creio q n precise
    public BigDecimal calcularLimiteUsado(UUID idPortador) {
        final List<CardEntity> cardEntities = cardRepository.findByIdPortador(idPortador);

        final BigDecimal limiteUsado = cardEntities.stream()
                .map(CardEntity::getLimit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // aq
        System.out.println("Teu limite " + limiteUsado);

        return null;
    }
}
