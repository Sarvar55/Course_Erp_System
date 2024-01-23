package com.erp.erpbackend.services.security;

import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.response.LoginResponse;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload loginPayload);

    LoginResponse refresh(RefreshTokenPayload refreshTokenPayload);

    void logout();

    void setAuthentication(String email);
}
