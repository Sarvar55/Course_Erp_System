package com.erp.erpbackend.services.security;

import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.payload.auth.SignUpPayload;
import com.erp.erpbackend.models.payload.otp.OtpPayload;
import com.erp.erpbackend.models.payload.otp.SignUpOTPRequest;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.models.response.ProceedKeyResponse;

public interface AuthBusinessService {

    LoginResponse login(LoginPayload loginPayload);

    LoginResponse refresh(RefreshTokenPayload refreshTokenPayload);

    void logout();

    ProceedKeyResponse signUp(SignUpPayload payload);

    void signUpOTP(OtpPayload payload);

    void setAuthentication(String email);

    void signUpOTPConfirmation(SignUpOTPRequest payload);
}
