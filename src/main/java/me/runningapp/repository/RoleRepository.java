package me.runningapp.repository;

import me.runningapp.model.authority.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

