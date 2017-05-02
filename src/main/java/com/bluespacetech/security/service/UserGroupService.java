package com.bluespacetech.security.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;

public interface UserGroupService {

	UserGroup findUserGroupByGroupName(final String groupName);

	List<UserGroup> findByGroupNameLike(final String groupName);

	List<UserGroup> findByDescriptionLike(final String description);

	List<UserGroup> getAllUserGroups();

	UserGroup getUserGroupById(final Long userGroupId);

	List<UserGroup> getUserGroupByIds(final List<Long> userGroupIds);

	UserGroup createUserGroup(final UserGroup userGroup)
			throws BusinessException;

	UserGroup updateUserGroup(final UserGroup userGroup)
			throws BusinessException;

	void deleteUserGroup(final Long userGroupId) ;

	List<UserGroup> findUserGroupsBySearchCriteria(final UserGroupSearchCriteria sectionSearchCriteria);
}