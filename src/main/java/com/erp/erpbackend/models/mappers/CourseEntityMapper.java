package com.erp.erpbackend.models.mappers;

import com.erp.erpbackend.models.mybatis.course.Course;
import com.erp.erpbackend.models.payload.auth.SignUpPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseEntityMapper {

    CourseEntityMapper INSTANCE = Mappers.getMapper(CourseEntityMapper.class);

    @Mapping(target = "name", source = "courseName")
    @Mapping(target = "status", constant = "ACTIVE")
    Course fromSignUpPayload(SignUpPayload payload);
}
