package com.erp.erpbackend.models.payload.auth.signup;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpPayload {
    String name;
    String surname;
    String email;
    String phoneNumber;
    String password;
    String courseName;
    String address;
}
