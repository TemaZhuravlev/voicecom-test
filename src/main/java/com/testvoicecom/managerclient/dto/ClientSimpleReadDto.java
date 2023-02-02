package com.testvoicecom.managerclient.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class ClientSimpleReadDto {

    UUID uuid;
    String fullName;
}
