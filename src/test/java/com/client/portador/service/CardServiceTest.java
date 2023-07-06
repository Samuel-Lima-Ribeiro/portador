package com.client.portador.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.response.CardResponse;
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
import java.time.LocalDate;
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

    private static final UUID ID_PORTADOR = UUID.fromString("438b2f95-4560-415b-98c2-9770cc1c4d93");
    @Captor
    private ArgumentCaptor<UUID> portadorIdArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> cardIdArgumentCaptor;
    @Captor
    private ArgumentCaptor<CardEntity> cardEntityArgumentCaptor;

    public static CardRequest cardRequestFactory() {
        return CardRequest.builder()
                .limit(BigDecimal.valueOf(1000.00))
                .build();
    }

    public static PortadorEntity portadorEntityFactory() {
        return PortadorEntity.builder()
                .clientId(ID_PORTADOR)
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
                cardService.criarCartao(ID_PORTADOR, cardRequestFactory()));

        assertEquals("Portador do id %s não encontrado".formatted(ID_PORTADOR) ,exception.getMessage());
    }

    @Test
    void deve_lancar_LimitInvalidException_ao_criar_um_cartao_com_limite_maior_que_do_portador() {
        final CardRequest cardRequest = CardRequest.builder().limit(BigDecimal.valueOf(100_000.00)).build();

        when(portadorRepository.findById(any())).thenReturn(Optional.ofNullable(portadorEntityFactory()));

        final LimitInvalidException exception = assertThrows(LimitInvalidException.class, () ->
                cardService.criarCartao(ID_PORTADOR, cardRequest));

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
                cardService.criarCartao(ID_PORTADOR, cardRequest));

        assertEquals("Soma dos limites de cartões ultrapassa limite do portador", exception.getMessage());
    }

    @Test
    void deve_criar_um_cartao() {
        final CardRequest cardRequest = cardRequestFactory();

        final CardEntity cardEntity = cardEntityFactory();
        when(portadorRepository.findById(portadorIdArgumentCaptor.capture())).thenReturn(Optional.ofNullable(portadorEntityFactory()));

        when(cardRepository.findByIdPortador(portadorIdArgumentCaptor.capture())).thenReturn(List.of(cardEntity, cardEntity));

        when(cardRepository.save(cardEntityArgumentCaptor.capture())).thenReturn(cardEntityFactory());

        final CardResponse cardResponse = cardService.criarCartao(ID_PORTADOR, cardRequest);
        final CardEntity entitySalvado = cardEntityArgumentCaptor.getValue();
        final LocalDate agora = LocalDate.now();

        assertEquals(ID_PORTADOR, portadorIdArgumentCaptor.getValue());
        assertEquals(agora.plusYears(5), entitySalvado.getDueDate());

        assertNotNull(cardResponse.cardId());
        assertNotNull(cardResponse.cardNumber());
        assertNotNull(cardResponse.cvv());
    }

    @Test
    void deve_buscar_todos_cartoes_de_um_portador() {
        final CardEntity cardEntity = cardEntityFactory();
        when(portadorRepository.existsById(portadorIdArgumentCaptor.capture())).thenReturn(true);
        when(cardRepository.findByIdPortador(portadorIdArgumentCaptor.capture())).thenReturn(List.of(cardEntity, cardEntity));

        final List<CardResponse> cardResponses = cardService.getAllCardByPortador(ID_PORTADOR);

        assertEquals(ID_PORTADOR, portadorIdArgumentCaptor.getValue());
        assertEquals(2 , cardResponses.size());
    }

    @Test
    void deve_retornar_CardHolderNotFoundException_ao_pesquisar_todos_os_cartoes_de_um_portador_inexistente() {
        when(portadorRepository.existsById(portadorIdArgumentCaptor.capture())).thenReturn(false);

        final CardHolderNotFoundException exception = assertThrows(CardHolderNotFoundException.class,
                ()-> cardService.getAllCardByPortador(ID_PORTADOR));

        assertEquals(ID_PORTADOR, portadorIdArgumentCaptor.getValue());
        assertEquals("Portador do id %s não encontrado".formatted(ID_PORTADOR), exception.getMessage());
    }
}