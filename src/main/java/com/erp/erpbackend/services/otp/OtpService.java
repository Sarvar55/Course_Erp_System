package com.erp.erpbackend.services.otp;


import com.erp.erpbackend.models.dto.SendOTPDto;

//factory pattern
public interface OtpService {

    void send(SendOTPDto dto);
}
