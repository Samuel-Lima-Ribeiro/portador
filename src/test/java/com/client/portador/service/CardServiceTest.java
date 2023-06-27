package com.client.portador.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.exception.LimitInvalidException;
import com.client.portador.mapper.CardEntityMapper;
import com.client.portador.mapper.CardEntityMapperImpl;
import com.client.portador.mapper.CardMapper;
import com.client.portador.mapper.CardMapperImpl;
import com.client.portador.mapper.CardResponseMapper;
import com.client.portador.mapper.CardResponseMapperImpl;
import com.client.portador.repository.CardRepository;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.CardEntity;
import com.client.portador.repository.entity.PortadorEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardServiceTest {
    @Mock
    private PortadorRepository portadorRepository;
    @Mock
    private CardRepository cardRepository;
    @Spy
    final CardMapper cardMapper = new CardMapperImpl();
    @Spy
    final CardEntityMapper cardEntityMapper = new CardEntityMapperImpl();
    @Spy
    final CardResponseMapper cardResponseMapper = new CardResponseMapperImpl();
    @InjectMocks
    private CardService cardService;

    private static final UUID ID = UUID.fromString("438b2f95-4560-415b-98c2-9770cc1c4d93");
    @Captor
    private ArgumentCaptor<UUID> portadorIdArgumentCaptor;

    public static CardRequest cardRequestFactory() {
        return CardRequest.builder()
                .limit(BigDecimal.valueOf(1000.00))
                .build();
    }

    public static PortadorEntity portadorEntityFactory() {
        return PortadorEntity.builder()
                .clientId(ID)
                .limit(BigDecimal.valueOf(10000.00))
                .build();
    }

    public static CardEntity cardEntityFactory() {
        return CardEntity.builder()
                .cardNumber("7620 4019 8165 5884")
                .limit(BigDecimal.valueOf(1000.00))
                .cvv(597)
                .build();
    }

    @Test
    void deve_lancar_CardHolderNotFoundException_ao_criar_um_cartao_com_id_do_portador_inexistente() {
        when(portadorRepository.findById(any())).thenReturn(Optional.empty());

        final CardHolderNotFoundException exception = assertThrows(CardHolderNotFoundException.class, () ->
                cardService.criarCartao(ID, cardRequestFactory()));

        assertEquals("Portador do id %s não encontrado".formatted(ID) ,exception.getMessage());
    }

    @Test
    void deve_lancar_LimitInvalidException_ao_criar_um_cartao_com_limite_maior_que_do_portador() {
        final CardRequest cardRequest = CardRequest.builder().limit(BigDecimal.valueOf(100_000.00)).build();

        when(portadorRepository.findById(any())).thenReturn(Optional.ofNullable(portadorEntityFactory()));

        final LimitInvalidException exception = assertThrows(LimitInvalidException.class, () ->
                cardService.criarCartao(ID, cardRequest));

        assertEquals("Limite solicitado ultrapassa limite do portador", exception.getMessage());
    }

    @Test
    void deve_lancar_LimitInvalidException_ao_criar_um_cartao_e_limite_superar_soma_de_limite_de_todos_os_cartoes() {
        final CardRequest cardRequest = CardRequest.builder()
                .limit(BigDecimal.valueOf(9_000.00))
                .build();
        final CardEntity cardEntity = cardEntityFactory();

        when(portadorRepository.findById(any())).thenReturn(Optional.ofNullable(portadorEntityFactory()));
        when(cardRepository.findByIdPortador(portadorIdArgumentCaptor.capture())).thenReturn(List.of(cardEntity, cardEntity));

        final LimitInvalidException exception = assertThrows(LimitInvalidException.class, () ->
                cardService.criarCartao(ID, cardRequest));

        assertEquals("Soma dos limites de cartões ultrapassa limite do portador", exception.getMessage());
    }
}