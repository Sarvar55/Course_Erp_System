package com.erp.erpbackend.models.payload.auth.signup;

import com.erp.erpbackend.models.enums.otp.OTPChannel;
import com.erp.erpbackend.models.response.ProceedKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor()
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpOtpChannelRequest extends ProceedKey {
    OTPChannel channel;
}
