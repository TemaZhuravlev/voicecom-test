package com.testvoicecom.managerclient.controller.rest;

import com.testvoicecom.managerclient.dto.ClientSimpleReadDto;
import com.testvoicecom.managerclient.dto.ManagerCreateEditDto;
import com.testvoicecom.managerclient.dto.ManagerFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.service.ManagerService;
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

@Tag(name = "Управление Менеджерами")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/managers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerRestController {

    private final ManagerService managerService;

    @Operation(summary = "Заведение (создания) данных нового Менеджера")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerReadDto create(@RequestBody @Valid ManagerCreateEditDto managerCreateEditDto) {
        return managerService.create(managerCreateEditDto);
    }

    @Operation(summary = "Получение списка Менеджеров с информацией о заместетеле")
    @GetMapping
    public List<ManagerFullInfoReadDto> getAll() {
        return managerService.getAll();
    }

    @Operation(summary = "Получение списка кодов и наименований Клиентов")
    @GetMapping("/{id}/clients")
    public List<ClientSimpleReadDto> getAllClientsForManager(@PathVariable Integer id) {
        return managerService.getAllClientsForManager(id);
    }

    @Operation(summary = "Обновление данных Менеджера")
    @PutMapping("/{id}")
    public ManagerReadDto update(@PathVariable Integer id, @RequestBody @Valid ManagerCreateEditDto managerDto) {
        return managerService.update(id, managerDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Удаление данных Менеджера и указание у Клиента данных заместителя")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return managerService.delete(id) ? noContent().build() : notFound().build();
    }
}
