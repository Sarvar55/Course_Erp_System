package com.erp.erpbackend.services.country;

import com.erp.erpbackend.models.mybatis.country.Country;

import java.util.List;

public interface CountryService {
    void insert(Country country);

    Country findById(long id);

    List<Country> findAll();

    void update(Country country);
}
