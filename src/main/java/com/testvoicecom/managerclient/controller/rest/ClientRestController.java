package com.testvoicecom.managerclient.controller.rest;

import com.testvoicecom.managerclient.dto.ClienFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ClientCardReadDto;
import com.testvoicecom.managerclient.dto.ClientCreateEditDto;
import com.testvoicecom.managerclient.dto.ClientReadDto;
import com.testvoicecom.managerclient.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@Tag(name = "Управление Клиентами")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRestController {

    private final ClientService clientService;

    @Operation(summary = "Создание Клиента с указанием менеджера")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientReadDto create(@RequestBody @Valid ClientCreateEditDto clientCreateDto) {
        return clientService.create(clientCreateDto);
    }

    @Operation(summary = "Получение списка Клиентов с данными Менеджера и нормера телефона его заместителя")
    @GetMapping
    public List<ClienFullInfoReadDto> getAll() {
        return clientService.getAll();
    }

    @Operation(summary = "Получение полного описания Клиента и его Менеджера")
    @GetMapping("/{id}")
    public ClientCardReadDto getById(@PathVariable Integer id) {
        return clientService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Изменение полного наименования Клиента и/или его юридического адреса")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientReadDto update(@PathVariable Integer id, @RequestBody @Valid ClientCreateEditDto clientEditDto) {
        return clientService.update(id, clientEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Удаление данных Клиента из списка представления")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return clientService.delete(id) ? noContent().build() : notFound().build();
    }
}
