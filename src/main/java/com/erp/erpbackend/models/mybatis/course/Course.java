package com.erp.erpbackend.models.mybatis.course;

import com.erp.erpbackend.models.enums.course.CourseStatus;
import com.erp.erpbackend.models.mybatis.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BaseEntity {
    String name;
    CourseStatus status;

}
