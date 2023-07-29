package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find a role by its name
    Role findByName(String name);
}
