package com.bluespacetech.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.repository.UserRoleRepository;
import com.bluespacetech.security.repository.UserRoleRepositoryCustom;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;

@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
		ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserRoleRepositoryCustom userRoleRepositoryCustom;

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public UserRole findUserRoleByRoleName(final String roleName) {
		return userRoleRepository.findUserRoleByRoleName(roleName);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public List<UserRole> findByDescriptionLike(final String description) {
		return userRoleRepository.findByDescriptionLike(description);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public List<UserRole> getAllUserRoles() {
		return userRoleRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public UserRole getUserRoleById(final Long userRoleId) {
		return userRoleRepository.findOne(userRoleId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_USER_ROLES'))")
	public UserRole createUserRole(final UserRole userRole) throws BusinessException {
		for(final UserRoleAuthority userRoleAuthority :userRole.getUserRoleAuthorities()){
			userRoleAuthority.setUserRole(userRole);
		}
		return userRoleRepository.save(userRole);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_USER_ROLES'))")
	public UserRole updateUserRole(final UserRole userRole) throws BusinessException {
		for(final UserRoleAuthority userRoleAuthority :userRole.getUserRoleAuthorities()){
			userRoleAuthority.setUserRole(userRole);
		}
		return userRoleRepository.save(userRole);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_USER_ROLES'))")
	public void deleteUserRole(final Long userRoleId) {
		userRoleRepository.delete(userRoleId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public List<UserRole> findByRoleNameLike(final String roleName) {
		return userRoleRepository.findByRoleNameLike(roleName);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public List<UserRole> findUserRolesBySearchCriteria(
			final UserRoleSearchCriteria sectionSearchCriteria) {
		return userRoleRepositoryCustom.findUserRolesBySearchCriteria(sectionSearchCriteria);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
	public List<UserRole> getUserRoleByIds(final List<Long> userRoleIds) {
		return  userRoleRepository.findAll(userRoleIds);
	}

}