package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    void insert(User user);
}
