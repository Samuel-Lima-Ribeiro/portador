package com.client.portador.controller;

import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.service.PortadorService;
import com.client.portador.utils.Status;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PortadorResponse createPortador(@RequestBody PortadorRequest portadorRequest) {
        return portadorService.criarPortador(portadorRequest);
    }

    @GetMapping
    public List<PortadorResponse> getAllPortadores(@RequestParam(value = "status", required = false)Status status) {
        return portadorService.getAllPortadoresBy(status);
    }
}
