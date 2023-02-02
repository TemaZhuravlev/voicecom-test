package com.testvoicecom.managerclient.service;

import com.testvoicecom.managerclient.dto.ClienFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ClientCardReadDto;
import com.testvoicecom.managerclient.dto.ClientCreateEditDto;
import com.testvoicecom.managerclient.dto.ClientReadDto;
import com.testvoicecom.managerclient.model.Client;
import com.testvoicecom.managerclient.repository.ClientRepository;
import com.testvoicecom.managerclient.util.mapper.ClientCardReadMapper;
import com.testvoicecom.managerclient.util.mapper.ClientCreateEditMapper;
import com.testvoicecom.managerclient.util.mapper.ClientFullInfoReadMapper;
import com.testvoicecom.managerclient.util.mapper.ClientReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientCreateEditMapper clientCreateEditMapper;
    private final ClientReadMapper clientReadMapper;
    private final ClientFullInfoReadMapper clientFullInfoReadMapper;
    private final ClientCardReadMapper clientWithManagerReadMapper;

    @Transactional
    public ClientReadDto create(ClientCreateEditDto clientCreateDto) {
        log.info("Создание Клиента с указанием менеджера");
        return Optional.of(clientCreateDto)
                .map(clientCreateEditMapper::map)
                .map(clientRepository::save)
                .map(clientReadMapper::map)
                .orElseThrow();
    }

    public List<ClienFullInfoReadDto> getAll() {
        log.info("Получение списка Клиентов с данными Менеджера и нормера телефона его заместителя");
        List<Client> clientList = clientRepository.getAll();
        return clientList.stream()
                .map(clientFullInfoReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ClientCardReadDto> getById(Integer id) {
        log.info("Получение полного описания Клиента c id={} и его Менеджера", id);
        return clientRepository.findById(id)
                .map(clientWithManagerReadMapper::map);
    }

    @Transactional
    public Optional<ClientReadDto> update(Integer id, ClientCreateEditDto cllientEditDto) {
        log.info("Изменение описания Клиента c id={}", id);
        return clientRepository.findById(id)
                .map(client -> clientCreateEditMapper.map(cllientEditDto, client))
                .map(clientRepository::saveAndFlush)
                .map(clientReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        log.info("Удаление данных Клиента c id={} из списка представления", id);
        return clientRepository.findById(id)
                .map(client -> {
                    client.setEnabled(false);
                    clientRepository.saveAndFlush(client);
                    return true;
                })
                .orElse(false);
    }
}
