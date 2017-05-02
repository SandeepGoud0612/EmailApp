package com.bluespacetech.security.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;

public interface UserRoleService {

	UserRole findUserRoleByRoleName(final String roleName);

	List<UserRole> findByRoleNameLike(final String roleName);

	List<UserRole> findByDescriptionLike(final String description);

	List<UserRole> getAllUserRoles();

	UserRole getUserRoleById(final Long userRoleId);

	List<UserRole> getUserRoleByIds(final List<Long> userRoleIds);

	UserRole createUserRole(final UserRole userRole)
			throws BusinessException;

	UserRole updateUserRole(final UserRole userRole)
			throws BusinessException;

	void deleteUserRole(final Long userRoleId) ;

	List<UserRole> findUserRolesBySearchCriteria(final UserRoleSearchCriteria sectionSearchCriteria);
}