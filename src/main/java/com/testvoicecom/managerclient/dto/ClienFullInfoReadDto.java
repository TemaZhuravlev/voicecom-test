package com.testvoicecom.managerclient.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class ClienFullInfoReadDto {
    UUID uuid;
    String fullName;
    String legalAddress;
    ManagerCardReadDto managerWithAlternate;
}
