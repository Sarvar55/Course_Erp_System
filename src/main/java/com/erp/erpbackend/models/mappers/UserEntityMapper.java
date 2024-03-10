package com.erp.erpbackend.models.mappers;

import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.payload.auth.signup.SignUpPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(target = "password", source = "encryptedPassword")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "roleId", source = "roleId")
    User fromSignUpPayloadToUser(SignUpPayload payload, String encryptedPassword, Long roleId);

}
