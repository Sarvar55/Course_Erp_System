package com.erp.erpbackend.models.enums.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorResponseMessages implements ResponseMessages {
    UNEXPECTED("UNEXPECTED", "unexpected", "Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("NOT_FOUND", "not_found_%s", "%s can't find by %s", HttpStatus.NOT_FOUND);
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
