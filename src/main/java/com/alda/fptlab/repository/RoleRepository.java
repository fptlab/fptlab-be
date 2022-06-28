package com.alda.fptlab.repository;

import com.alda.fptlab.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String roleName);
}
