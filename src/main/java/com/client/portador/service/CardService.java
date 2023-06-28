package com.client.portador.service;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.response.CardResponse;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.exception.LimitInvalidException;
import com.client.portador.mapper.CardEntityMapper;
import com.client.portador.mapper.CardMapper;
import com.client.portador.mapper.CardResponseMapper;
import com.client.portador.model.Card;
import com.client.portador.repository.CardRepository;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.CardEntity;
import com.client.portador.repository.entity.PortadorEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PortadorRepository portadorRepository;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardEntityMapper cardEntityMapper;
    private final CardResponseMapper cardResponseMapper;

    public CardResponse criarCartao(UUID idPortador, CardRequest cardRequest) {
        final BigDecimal limiteCartaoSolicitado = cardRequest.limit();

        final BigDecimal limitePortador = checarPortador(idPortador, limiteCartaoSolicitado);

        calcularLimiteUsado(idPortador, limiteCartaoSolicitado, limitePortador);

        final Card card = cardMapper.from(cardRequest);

        final Card cardUpdate = card.gerarInformacoesCartao(idPortador);

        final CardEntity cardEntity = cardEntityMapper.from(cardUpdate);

        final CardEntity cardEntitySalvo = cardRepository.save(cardEntity);

        return cardResponseMapper.from(cardEntitySalvo);
    }

    private BigDecimal checarPortador(UUID idPortador, BigDecimal limiteSolicitadoCartao) {
        final PortadorEntity portadorEntity = portadorRepository.findById(idPortador).orElseThrow(() ->
                new CardHolderNotFoundException("Portador do id %s não encontrado".formatted(idPortador)));

        final BigDecimal limitePortador = portadorEntity.getLimit();

        final Integer verificandoLimiteCartao = limiteSolicitadoCartao.compareTo(limitePortador);

        if (verificandoLimiteCartao > 0) {
            throw new LimitInvalidException("Limite solicitado ultrapassa limite do portador");
        }

        return limitePortador;
    }

    private void calcularLimiteUsado(UUID idPortador, BigDecimal limiteCartaoSolicitado, BigDecimal limitePortador) {
        final List<CardEntity> cardEntities = cardRepository.findByIdPortador(idPortador);

        final BigDecimal limiteUsado = cardEntities.stream()
                .map(CardEntity::getLimit)
                .reduce(limiteCartaoSolicitado, BigDecimal::add);

        final Integer verificandoLimiteUsado = limiteUsado.compareTo(limitePortador);

        if (verificandoLimiteUsado > 0) {
            throw new LimitInvalidException("Soma dos limites de cartões ultrapassa limite do portador");
        }
    }

    public List<CardResponse> getAllCardByPortador(UUID idPortador) {
        final List<CardEntity> cardEntities = cardRepository.findByIdPortador(idPortador);

        return cardEntities.stream()
                .map(cardResponseMapper::from)
                .collect(Collectors.toList());
    }
}
