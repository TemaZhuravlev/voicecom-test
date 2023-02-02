package com.testvoicecom.managerclient.service;

import com.testvoicecom.managerclient.dto.ClienFullInfoReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientServiceTest extends AbstractServiceTest {
    private static final Integer CLIENT_ID = 1;

    @Autowired
    private ClientService clientService;

    @Test
    void findAll() {
        List<ClienFullInfoReadDto> all = clientService.getAll();
        assertThat(all).hasSize(3);
    }

    @Test
    void delete() {
        clientService.delete(CLIENT_ID);
        assertTrue(clientService.getById(CLIENT_ID).isPresent());
        assertThat(clientService.getAll()).hasSize(2);
    }
}