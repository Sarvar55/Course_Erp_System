package com.erp.erpbackend.services.base;

public interface TokenGenerator<T> {
    String generate(T obj);
}
