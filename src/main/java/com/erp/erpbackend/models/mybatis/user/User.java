package com.erp.erpbackend.models.mybatis.user;

import com.erp.erpbackend.models.enums.user.UserStatus;
import com.erp.erpbackend.models.mybatis.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    String name;
    String surname;
    UserStatus status;
    String email;
    String password;
    String phoneNumber;
    Long roleId;

}
