package com.erp.erpbackend.services.user;

import com.erp.erpbackend.models.mybatis.user.User;

public interface UserService {

    void insert(User user);

    User getById(Long id);

    User getByEmail(String email);

    boolean checkByEmail(String email);

    void update(User user);
}
