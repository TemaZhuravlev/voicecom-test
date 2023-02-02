package com.testvoicecom.managerclient.dto;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ClientCreateEditDto {

    @NotBlank(message = "Поле 'ФИО' не может быть пустым")
    String fullName;

    @NotBlank(message = "Поле 'Юридический адрес' не может быть пустым")
    String legalAddress;

    @NotNull(message = "Поле 'Индетификатор Менеджера' не может быть пустым")
    @Min(value = 1, message = "'Индетификатор Менеджера' может быть только положительным целым числом")
    Integer managerId;
}
