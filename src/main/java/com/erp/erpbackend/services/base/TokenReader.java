package com.erp.erpbackend.services.base;

public interface TokenReader<T> {
    T read(String token);
}
