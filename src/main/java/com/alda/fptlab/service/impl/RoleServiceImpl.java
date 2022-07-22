package com.alda.fptlab.service.impl;

import com.alda.fptlab.entity.Role;
import com.alda.fptlab.repository.RoleRepository;
import com.alda.fptlab.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> fetchRoleList() {
        log.info("Fetching role list");
        return roleRepository.findAll();
    }
}
