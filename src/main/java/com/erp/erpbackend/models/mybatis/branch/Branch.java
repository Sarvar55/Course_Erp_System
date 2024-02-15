package com.erp.erpbackend.models.mybatis.branch;

import com.erp.erpbackend.models.enums.branch.BranchStatus;
import com.erp.erpbackend.models.mybatis.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Branch extends BaseEntity {
    String name;
    BranchStatus status;
    String address;
    double lat;
    double lon;
    Long courseId;
}
