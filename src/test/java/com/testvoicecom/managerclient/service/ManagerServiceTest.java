package com.testvoicecom.managerclient.service;

import com.testvoicecom.managerclient.model.Client;
import com.testvoicecom.managerclient.model.Manager;
import com.testvoicecom.managerclient.repository.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceTest extends AbstractServiceTest {
    private static final Integer MANAGER_ID = 2;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagerRepository managerRepository;

    @Test
    @Transactional
    void delete() {
        Manager managerWithClients = managerRepository.getWithClients(MANAGER_ID);
        List<Client> clientsByManager = managerWithClients.getClients();
        Integer alternateManagerId = managerWithClients.getAlternate().getId();

        assertTrue(managerService.delete(MANAGER_ID));

        List<Client> clientsByAlternateManager = managerRepository.getWithClients(alternateManagerId).getClients();

        assertTrue(clientsByAlternateManager.containsAll(clientsByManager));
        assertThat(managerService.getAll()).hasSize(2);
    }
}