package com.erp.erpbackend.controller;

import com.erp.erpbackend.models.base.BaseResponse;
import com.erp.erpbackend.models.dto.RefreshTokenDto;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.services.security.AccessTokenManager;
import com.erp.erpbackend.services.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload) {
        User user = User.builder().email("email@gmail.com").build();
        user.setId(1L);
        return BaseResponse.success(
                LoginResponse.builder().accessToken(accessTokenManager.generate(user))
                        .refreshToken(refreshTokenManager.generate(
                                RefreshTokenDto.builder().rememberMe(loginPayload.isRememberMe())
                                        .user(user).build()
                        )).build()
        );
    }
}
