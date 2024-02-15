package com.erp.erpbackend.controller;

import com.erp.erpbackend.models.base.BaseResponse;
import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.payload.auth.SignUpPayload;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.services.security.AuthBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload) {
        return BaseResponse.success(authBusinessService.login(loginPayload));
    }

    @PostMapping("/token/refresh")
    public BaseResponse<LoginResponse> refresh(@RequestBody RefreshTokenPayload refreshTokenPayload) {
        return BaseResponse.success(authBusinessService.refresh(refreshTokenPayload));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout() {
        authBusinessService.logout();
        return BaseResponse.success();
    }

    @PostMapping("/sign-up")
    public BaseResponse<Void> signUp(@RequestBody SignUpPayload payload) {
        authBusinessService.signUp(payload);
        return BaseResponse.success();
    }
}
