package com.erp.erpbackend.models.enums.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessages {

    String key();

    String code();

    String message();

    HttpStatus status();
}
