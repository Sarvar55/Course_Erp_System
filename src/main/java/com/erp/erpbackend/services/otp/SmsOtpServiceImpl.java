package com.erp.erpbackend.services.otp;

import com.erp.erpbackend.models.dto.SendOTPDto;
import com.erp.erpbackend.services.redis.RedisService;
import com.erp.erpbackend.utils.OTPUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsOtpServiceImpl implements OtpService {

    private final RedisService redisService;

    @Override
    public void send(SendOTPDto dto) {
        String otp = OTPUtils.generate();
        redisService.set(dto.getKey(), otp, 5);
        log.info("OTP sent via Email to:{},OTP:{}", dto.getTarget(), otp);
    }
}
