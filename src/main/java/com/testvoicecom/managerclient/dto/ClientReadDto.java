package com.testvoicecom.managerclient.dto;

import lombok.Value;

import java.util.UUID;
@Value
public class ClientReadDto {

    UUID uuid;
    String fullName;
    String legalAddress;
}
