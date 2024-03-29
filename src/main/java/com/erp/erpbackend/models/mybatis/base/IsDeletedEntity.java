package com.erp.erpbackend.models.mybatis.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IsDeletedEntity {
    boolean isDeleted;
}
