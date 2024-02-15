package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.employee.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeRepository {

    void insert(Employee employee);
}
