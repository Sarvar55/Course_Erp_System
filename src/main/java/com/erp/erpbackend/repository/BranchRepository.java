package com.erp.erpbackend.repository;

import com.erp.erpbackend.models.mybatis.branch.Branch;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BranchRepository {

    void insert(Branch branch);
}
