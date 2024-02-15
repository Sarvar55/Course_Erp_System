package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserRepository {
    void insert(User user);

    //burada olan @Param("email") mybatis tarafında match işlemi için kullanılıyor. burada ne yazıldıysa diger tarafda da parametre olarak onu almak lazım
    Optional<User> findByEmail(@Param("email") String email);
}
