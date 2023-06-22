package com.client.portador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.client.portador.apicreditanalysis.ApiCreditAnalysis;
import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisDeniedException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import com.client.portador.mapper.PortadorEntityMapper;
import com.client.portador.mapper.PortadorEntityMapperImpl;
import com.client.portador.mapper.PortadorMapper;
import com.client.portador.mapper.PortadorMapperImpl;
import com.client.portador.mapper.PortadorResponseMapper;
import com.client.portador.mapper.PortadorResponseMapperImpl;
import com.client.portador.repository.PortadorRepository;
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
class PortadorServiceTest {
    @Mock
    PortadorRepository portadorRepository;
    @Mock
    ApiCreditAnalysis apiCreditAnalysis;
    @Spy
    private PortadorEntityMapper portadorEntityMapper = new PortadorEntityMapperImpl();
    @Spy
    private PortadorMapper portadorMapper = new PortadorMapperImpl();
    @Spy
    private PortadorResponseMapper portadorResponseMapper = new PortadorResponseMapperImpl();
    @InjectMocks
    private PortadorService portadorService;
    @Captor
    private ArgumentCaptor<UUID> creditAnalysisIdArgumentCaptor;

    // cenario baum
    public static CreditAnalysisDto creditAnalysisDtoFactory() {
        return CreditAnalysisDto.builder()
                .id(UUID.fromString("20260876-eead-4bb0-8ec6-8f9d0f2596eb"))
                .clientId(UUID.fromString("93e6b252-b810-427c-a489-58760ab51f97"))
                .approvedLimit(100.00)
                .approved(true)
                .build();
    }

    public static PortadorRequest portadorRequestFactory() {
        return PortadorRequest.builder()
                .clientId(UUID.fromString("93e6b252-b810-427c-a489-58760ab51f97"))
                .creditAnalysisId(UUID.fromString("20260876-eead-4bb0-8ec6-8f9d0f2596eb"))
                .bankAccount(PortadorRequest.BankAccountRequest.builder()
                        .account("111111-1")
                        .agency("1111")
                        .bankCode("123").build()).build();
    }

    @Test
    void deve_lancar_creditAnalysisNotFoundException_ao_consultar_api_por_id_da_analise_inexistente() {
        PortadorRequest portadorRequest = portadorRequestFactory();
        when(apiCreditAnalysis.getCreditAnalysis(creditAnalysisIdArgumentCaptor.capture())).thenReturn(CreditAnalysisDto.builder().build());

        final CreditAnalysisNotFoundException exception = assertThrows(CreditAnalysisNotFoundException.class
            ,() -> portadorService.criarPortador(portadorRequest));

        assertEquals(portadorRequest.creditAnalysisId() ,creditAnalysisIdArgumentCaptor.getValue());
        assertEquals("Análise de Crédito não encontrada pelo id %s".formatted(portadorRequest.creditAnalysisId()) ,exception.getMessage());
    }

    @Test
    void deve_lancar_clientInvalidException_ao_consultar_analise_de_credito_e_id_do_cliente_estiver_diferente_do_id_da_request() {
        PortadorRequest portadorRequest = portadorRequestFactory();
        CreditAnalysisDto dto = CreditAnalysisDto.builder()
                .id(UUID.fromString("20260876-eead-4bb0-8ec6-8f9d0f2596eb"))
                .clientId(UUID.fromString("30e0853d-4dde-49e3-baea-db0f1606b8ad"))
                .approvedLimit(100.00)
                .approved(true)
                .build();
        when(apiCreditAnalysis.getCreditAnalysis(creditAnalysisIdArgumentCaptor.capture())).thenReturn(dto);

        final ClientInvalidException exception = assertThrows(ClientInvalidException.class
                ,() -> portadorService.criarPortador(portadorRequest));

        assertEquals(portadorRequest.creditAnalysisId() ,creditAnalysisIdArgumentCaptor.getValue());
        assertEquals("Cliente do id %s não corresponde ao cliente da Análise".formatted(portadorRequest.clientId()) ,exception.getMessage());
    }

    @Test
    void deve_lancar_CreditAnalysisDeniedException_ao_consultar_analise_de_credito_e_aprovado_estiver_como_false() {
        PortadorRequest portadorRequest = portadorRequestFactory();
        CreditAnalysisDto dto = CreditAnalysisDto.builder()
                .id(UUID.fromString("20260876-eead-4bb0-8ec6-8f9d0f2596eb"))
                .clientId(UUID.fromString("93e6b252-b810-427c-a489-58760ab51f97"))
                .approvedLimit(100.00)
                .approved(false)
                .build();
        when(apiCreditAnalysis.getCreditAnalysis(creditAnalysisIdArgumentCaptor.capture())).thenReturn(dto);

        final CreditAnalysisDeniedException exception = assertThrows(CreditAnalysisDeniedException.class
                ,() -> portadorService.criarPortador(portadorRequest));

        assertEquals(portadorRequest.creditAnalysisId() ,creditAnalysisIdArgumentCaptor.getValue());
        assertEquals("Análise de Crédito do id %s não aprovada".formatted(portadorRequest.creditAnalysisId()) ,exception.getMessage());
    }
}