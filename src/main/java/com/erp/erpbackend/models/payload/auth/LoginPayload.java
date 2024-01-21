package com.erp.erpbackend.models.payload.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPayload {
    //ToDo: validation
    String email;
    String password;
    boolean rememberMe;
}
