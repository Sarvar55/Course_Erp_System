package com.erp.erpbackend.models.payload.otp;

import com.erp.erpbackend.models.enums.otp.OTPChannel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor()
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpPayload {
    OTPChannel channel;
}
