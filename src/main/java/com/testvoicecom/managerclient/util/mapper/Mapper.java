package com.testvoicecom.managerclient.util.mapper;

public interface Mapper<F, T> {
    T map(F object);
}
