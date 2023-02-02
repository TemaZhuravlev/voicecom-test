package com.testvoicecom.managerclient.service;

import com.testvoicecom.managerclient.dto.ClientSimpleReadDto;
import com.testvoicecom.managerclient.dto.ManagerCreateEditDto;
import com.testvoicecom.managerclient.dto.ManagerFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.model.Manager;
import com.testvoicecom.managerclient.repository.ClientRepository;
import com.testvoicecom.managerclient.repository.ManagerRepository;
import com.testvoicecom.managerclient.util.mapper.ManagerCreateEditMapper;
import com.testvoicecom.managerclient.util.mapper.ManagerFullInfoMapper;
import com.testvoicecom.managerclient.util.mapper.ManagerReadMapper;
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
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ClientRepository clientRepository;
    private final ManagerCreateEditMapper managerCreateEditMapper;
    private final ManagerReadMapper managerReadMapper;
    private final ManagerFullInfoMapper managerFullInfoMapper;

    @Transactional
    public ManagerReadDto create(ManagerCreateEditDto managerCreateEditDto) {
        log.info("Создание нового менеджера");
        return Optional.ofNullable(managerCreateEditDto)
                .map(managerCreateEditMapper::map)
                .map(managerRepository::save)
                .map(managerReadMapper::map)
                .orElseThrow();
    }

    public List<ManagerFullInfoReadDto> getAll() {
        log.info("Получение списка Менеджеров с информацией о заместетеле");
        List<Manager> managerList = managerRepository.findAll();
        return managerList.stream()
                .map(managerFullInfoMapper::map)
                .collect(Collectors.toList());
    }

    public List<ClientSimpleReadDto> getAllClientsForManager(Integer id) {
        log.info("Получение списка кодов и наименований Клиентов у менеджера с c id={}", id);
        Manager manager = managerRepository.getWithClients(id);
        return manager.getClients().stream()
                .map(client -> new ClientSimpleReadDto(client.getUuid(), client.getFullName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ManagerReadDto> update(Integer id, ManagerCreateEditDto managerDto) {
        log.info("Обновление данных Менеджера с id={}", id);
        return managerRepository.findById(id)
                .map(manager -> managerCreateEditMapper.map(managerDto, manager))
                .map(managerRepository::saveAndFlush)
                .map(managerReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        log.info("Удаление данных Менеджера с id={} и указание у Клиента данных заместителя", id);
        Manager manager = managerRepository.getWithClients(id);
        Manager alternateManager = manager.getAlternate();
        if (alternateManager != null) {
            manager.getClients()
                    .forEach(client -> {
                        client.setManager(alternateManager);
                        clientRepository.saveAndFlush(client);
                    });
/**
 * замена на текущего заместителя для всех записей у которых удвляемый менеджер был заместителем
 */
//            managerRepository.updateAlternate(manager.getId(), alternateManager);
/**
 * обнуление поля alternate_id для всех записей у которых удвляемый менеджер был заместителем
 */
            managerRepository.deleteAlternateId(manager.getId());
            managerRepository.delete(manager);
            return true;
        }
        return false;
    }
}
