package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.country.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CountryRepository {

    void insert(Country country);

    Optional<Country> findById(@Param("id") long id);

    List<Country> findAll();

    void update(Country country);
}
