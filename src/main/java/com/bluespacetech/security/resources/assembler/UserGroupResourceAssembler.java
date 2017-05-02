/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bluespacetech.security.controller.UserGroupController;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.resources.UserGroupResource;
import com.bluespacetech.security.resources.UserGroupUserRoleResource;
import com.bluespacetech.security.resources.UserRoleResource;

/**
 * @author pradeep created date 27-Jan-2015
 */
public class UserGroupResourceAssembler extends
ResourceAssemblerSupport<UserGroup, UserGroupResource> {

	public UserGroupResourceAssembler() {
		super(UserGroupController.class, UserGroupResource.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object
	 * )
	 */
	@Override
	public UserGroupResource toResource(final UserGroup userGroup) {
		final UserGroupResource userGroupResource = new UserGroupResource();
		userGroupResource.setObjectId(userGroup.getId());
		userGroupResource.setVersion(userGroup.getVersion());
		userGroupResource.setGroupName(userGroup.getGroupName());
		userGroupResource.setDescription(userGroup.getDescription());
		final Link link = linkTo(UserGroupController.class).slash(
				userGroup.getId()).withSelfRel();
		userGroupResource.add(link.withSelfRel());
		return userGroupResource;
	}

	public UserGroupResource toCompleteResource(final UserGroup userGroup,final Map<Long,UserRole> userRolesMap) {
		final UserGroupResource userGroupResource = this.toResource(userGroup);
		final List<UserGroupUserRoleResource> userGroupUserRoleResources = new ArrayList<UserGroupUserRoleResource>();
		final UserRoleResourceAssembler userRoleResourceAssembler = new UserRoleResourceAssembler();
		for(final UserGroupUserRole userGroupUserRole:userGroup.getUserGroupUserRoles()){
			final UserGroupUserRoleResource userGroupUserRoleResource = new UserGroupUserRoleResource();
			final UserRoleResource userRoleResource = userRoleResourceAssembler.toCompleteResource(userRolesMap.get(userGroupUserRole.getUserRoleId()));
			userGroupUserRoleResource.setUserRole(userRoleResource);
			userGroupUserRoleResource.setObjectId(userGroupUserRole.getId());
			userGroupUserRoleResources.add(userGroupUserRoleResource);
		}
		userGroupResource.setUserGroupUserRoles(userGroupUserRoleResources);
		return userGroupResource;
	}

	/**
	 * Copy all the properties from source Financial year to destination
	 * Financial year.
	 *
	 * @param sourceUserGroup
	 *            source Financial year.
	 * @param destinationUserGroup
	 *            destination Financial year.
	 */
	public static void copyUserGroupInto(
			final UserGroup sourceUserGroup,
			final UserGroup destinationUserGroup) {
		destinationUserGroup.setGroupName(sourceUserGroup.getGroupName());
		destinationUserGroup.setDescription(sourceUserGroup.getDescription());

		final Collection<UserGroupUserRole> userGroupUserRoleToPersist = new ArrayList<UserGroupUserRole>();
		final Map<Long,UserGroupUserRole> existingUserGroupUserRole = new HashMap<Long,UserGroupUserRole>();

		for(final UserGroupUserRole userGroupUserRole:destinationUserGroup.getUserGroupUserRoles()){
			existingUserGroupUserRole.put(userGroupUserRole.getId(),userGroupUserRole);
		}

		for(final UserGroupUserRole newUserGroupUserRole : sourceUserGroup.getUserGroupUserRoles()){
			if(newUserGroupUserRole.getResourceObjectId()!=null && existingUserGroupUserRole.containsKey(newUserGroupUserRole.getResourceObjectId())) {
				userGroupUserRoleToPersist.add(existingUserGroupUserRole.get(newUserGroupUserRole.getResourceObjectId()));
			}else{
				final UserGroupUserRole userGroupUserRole = new UserGroupUserRole();
				userGroupUserRole.setUserRoleId(newUserGroupUserRole.getUserRoleId());
				userGroupUserRoleToPersist.add(userGroupUserRole);
			}
		}
		destinationUserGroup.getUserGroupUserRoles().clear();
		destinationUserGroup.getUserGroupUserRoles().addAll(userGroupUserRoleToPersist);
	}

	/**
	 * Get the Financial Year from resource.
	 *
	 * @param userGroupResource
	 * @return
	 */
	public static UserGroup getUserGroupFromResource(
			final UserGroupResource userGroupResource) {
		final UserGroup destinationUserGroup = new UserGroup();
		destinationUserGroup.setGroupName(userGroupResource.getGroupName());
		destinationUserGroup.setDescription(userGroupResource.getDescription());
		final Collection<UserGroupUserRole> userGroupUserRoles  = new ArrayList<UserGroupUserRole>();

		for(final UserGroupUserRoleResource userGroupUserRoleResource :userGroupResource.getUserGroupUserRoles()){
			final UserGroupUserRole userGroupUserRole = new UserGroupUserRole();
			userGroupUserRole.setUserRoleId(userGroupUserRoleResource.getUserRole().getObjectId());
			userGroupUserRole.setResourceObjectId(userGroupUserRoleResource.getObjectId());
			userGroupUserRoles.add(userGroupUserRole);
		}
		destinationUserGroup.setUserGroupUserRole(userGroupUserRoles);;
		return destinationUserGroup;
	}

}
