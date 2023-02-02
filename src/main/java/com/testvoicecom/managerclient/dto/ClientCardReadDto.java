package com.testvoicecom.managerclient.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class ClientCardReadDto {
    UUID uuid;
    String fullName;
    String legalAddress;
    ManagerReadDto manager;
}
