package com.testvoicecom.managerclient.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class ManagerCreateEditDto {

    @NotBlank(message = "Поле 'ФИО' не может быть пустым")
    String fullName;

    @NotBlank(message = "Поле 'Номер телефона' не может быть пустым")
    @Pattern(message = "Поле 'Номер телефона' должено состоять только из цифр", regexp = "^\\d+$")
    String phoneNumber;

    Integer alternateManagerId;
}
