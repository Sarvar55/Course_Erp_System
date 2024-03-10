package com.erp.erpbackend.models.payload.auth.signup;

import com.erp.erpbackend.models.response.ProceedKey;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class SignUpOTPRequest extends ProceedKey {
    String otp;

}
