package com.erp.erpbackend.services.role;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.models.mybatis.role.Role;
import com.erp.erpbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final static String OWNER = "OWNER";
    private final RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName(OWNER)
                .orElseThrow(BaseException::unexpected);
    }
}
