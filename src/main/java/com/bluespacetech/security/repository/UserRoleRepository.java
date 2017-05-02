package com.bluespacetech.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findUserRoleByRoleName(final String roleName);

	List<UserRole> findByDescriptionLike(final String description);

	List<UserRole> findByRoleNameLike(final String roleName);
}
