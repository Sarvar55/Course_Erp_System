package com.erp.erpbackend.models.enums.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SuccessResponseMessages implements ResponseMessages {
    SUCCESS("SUCCESS", "success", "Successfully", HttpStatus.OK);

    String code;
    String key;
    String message;
    HttpStatus status;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
