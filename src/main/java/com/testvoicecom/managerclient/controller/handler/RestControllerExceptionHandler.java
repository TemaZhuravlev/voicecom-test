package com.testvoicecom.managerclient.controller.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice(basePackages = "com.testvoicecom.managerclient.controller.rest")
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleExceptions(MethodArgumentNotValidException exception) {
        return Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
    }
}
