package com.erp.erpbackend.models.porperties.otp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OTPJwtData {
    String secretKey;
}
