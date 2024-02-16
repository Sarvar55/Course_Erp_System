package com.erp.erpbackend.services.user;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.models.base.BaseResponse;
import com.erp.erpbackend.models.enums.response.ErrorResponseMessages;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.security.LoggedInUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username);


        if (!user.isActive()) {
            throw BaseException.of(ErrorResponseMessages.USER_NOT_ACTIVE);
        }

        return new LoggedInUserDetails(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
