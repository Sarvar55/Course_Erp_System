package com.erp.erpbackend.services.security;

import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.payload.auth.signup.SignUpPayload;
import com.erp.erpbackend.models.payload.auth.signup.SignUpOtpChannelRequest;
import com.erp.erpbackend.models.payload.auth.signup.SignUpOTPRequest;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.models.response.ProceedKey;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload loginPayload);

    LoginResponse refresh(RefreshTokenPayload refreshTokenPayload);

    void logout();

    ProceedKey signUp(SignUpPayload payload);

    void signUpOTP(SignUpOtpChannelRequest payload);

    void setAuthentication(String email);

    void signUpOTPConfirmation(SignUpOTPRequest payload);
}
