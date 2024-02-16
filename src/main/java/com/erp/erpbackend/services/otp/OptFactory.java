package com.erp.erpbackend.services.otp;

import com.erp.erpbackend.models.enums.otp.OTPChannel;
import org.springframework.stereotype.Service;

@Service
public class OptFactory {

    private static EmailOtpServiceImpl emailOtpService;
    private static SmsOtpServiceImpl smsOtpService;

    public OptFactory(EmailOtpServiceImpl emailOtpService, SmsOtpServiceImpl smsOtpService) {
        OptFactory.emailOtpService = emailOtpService;
        OptFactory.smsOtpService = smsOtpService;
    }

    public static OtpService handle(OTPChannel channel) {
        return switch (channel) {
            case SMS -> smsOtpService;
            case EMAIL -> emailOtpService;

        };
    }

}
