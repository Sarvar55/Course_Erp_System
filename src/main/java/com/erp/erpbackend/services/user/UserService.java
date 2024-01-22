package com.erp.erpbackend.services.user;

import com.erp.erpbackend.models.mybatis.user.User;

public interface UserService {

    void insert(User user);

    User getByEmail(String email);
}
