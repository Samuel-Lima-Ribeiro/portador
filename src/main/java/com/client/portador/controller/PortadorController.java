package com.client.portador.controller;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.request.LimitUpdateRequest;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.CardResponse;
import com.client.portador.controller.response.LimitUpdateResponse;
import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.service.CardService;
import com.client.portador.service.PortadorService;
import com.client.portador.utils.Status;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1.0/card-holders")
@RequiredArgsConstructor
public class PortadorController {

    private final PortadorService portadorService;
    private final CardService cardService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PortadorResponse createPortador(@RequestBody PortadorRequest portadorRequest) {
        return portadorService.criarPortador(portadorRequest);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PortadorResponse> getAllPortadores(@RequestParam(value = "status", required = false)Status status) {
        return portadorService.getAllPortadoresBy(status);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PortadorResponse getPortador(@PathVariable(value = "id")UUID id) {
        return portadorService.getPortadorById(id);
    }

    @PostMapping(path = "/{cardHolderId}/cards")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CardResponse createCard(@PathVariable(value = "cardHolderId") UUID cardHolderId, @RequestBody CardRequest cardRequest) {
        return cardService.criarCartao(cardHolderId, cardRequest);
    }

    @GetMapping(path = "/{cardHolderId}/cards")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CardResponse> getAllCardByPortador(@PathVariable(value = "cardHolderId") UUID cardHolderId) {
        return cardService.getAllCardByPortador(cardHolderId);
    }

    @GetMapping(path = "/{cardHolderId}/cards/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CardResponse getCardById(@PathVariable(value = "cardHolderId") UUID cardHolderId, @PathVariable(value = "id") UUID idCard) {
        return cardService.getCardById(cardHolderId, idCard);
    }

    @PatchMapping(path = "/{cardHolderId}/cards/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public LimitUpdateResponse patchlimit(@PathVariable(value = "cardHolderId") UUID cardHolderId, @PathVariable(value = "id") UUID idCard,
                                          @RequestBody LimitUpdateRequest limitUpdateRequest) {
        return cardService.atualizarLimiteCartao(cardHolderId, idCard, limitUpdateRequest);
    }
}
