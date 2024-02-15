package com.erp.erpbackend.models.payload.student;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentPayload {

    //todo: validation
    String name;
    String surname;
    String email;

    String phoneNumber;

}
