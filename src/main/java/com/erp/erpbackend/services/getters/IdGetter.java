package com.erp.erpbackend.services.getters;

public interface IdGetter<ID> {
    ID getId(String token);
}
