package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.course.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseRepository {

    void insert(Course course);
}
