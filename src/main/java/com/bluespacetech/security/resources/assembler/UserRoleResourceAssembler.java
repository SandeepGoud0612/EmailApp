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

import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.controller.UserRoleController;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.resources.UserRoleResource;

/**
 * @author pradeep created date 27-Jan-2015
 */
public class UserRoleResourceAssembler extends
ResourceAssemblerSupport<UserRole, UserRoleResource> {

	public UserRoleResourceAssembler() {
		super(UserRoleController.class, UserRoleResource.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object
	 * )
	 */
	@Override
	public UserRoleResource toResource(final UserRole userRole) {
		final UserRoleResource userRoleResource = new UserRoleResource();
		userRoleResource.setObjectId(userRole.getId());
		userRoleResource.setVersion(userRole.getVersion());
		userRoleResource.setRoleName(userRole.getRoleName());
		userRoleResource.setDescription(userRole.getDescription());
		final Link link = linkTo(UserRoleController.class).slash(
				userRole.getId()).withSelfRel();
		userRoleResource.add(link.withSelfRel());
		return userRoleResource;
	}

	public UserRoleResource toCompleteResource(final UserRole userRole) {
		final UserRoleResource userRoleResource = this.toResource(userRole);
		final List<String> userRoleAuthorities = new ArrayList<String>();
		for(final UserRoleAuthority userRoleAuthority:userRole.getUserRoleAuthorities()){
			userRoleAuthorities.add(userRoleAuthority.getAuthorityGrant().toString());
		}
		userRoleResource.setUserRoleAuthorities(userRoleAuthorities);
		return userRoleResource;
	}

	/**
	 * Copy all the properties from source Financial year to destination
	 * Financial year.
	 *
	 * @param sourceUserRole
	 *            source Financial year.
	 * @param destinationUserRole
	 *            destination Financial year.
	 */
	public static void copyUserRoleInto(
			final UserRole sourceUserRole,
			final UserRole destinationUserRole) {
		destinationUserRole.setRoleName(sourceUserRole.getRoleName());
		destinationUserRole.setDescription(sourceUserRole.getDescription());
		final Collection<UserRoleAuthority> userRoleAuthoritiesToPersist = new ArrayList<UserRoleAuthority>();
		final Map<String,UserRoleAuthority> existingUserRoleAuthorities = new HashMap<String,UserRoleAuthority>();
		for(final UserRoleAuthority exitingUserRoleAuthority:destinationUserRole.getUserRoleAuthorities()){
			existingUserRoleAuthorities.put(exitingUserRoleAuthority.getAuthorityGrant().toString(),exitingUserRoleAuthority);
		}
		for(final UserRoleAuthority newUserRoleAuthority:sourceUserRole.getUserRoleAuthorities()){
			if(existingUserRoleAuthorities.containsKey(newUserRoleAuthority.getAuthorityGrant().toString())) {
				userRoleAuthoritiesToPersist.add(existingUserRoleAuthorities.get(newUserRoleAuthority.getAuthorityGrant().toString()));
			}else{
				final UserRoleAuthority userRoleAuthority = new UserRoleAuthority();
				userRoleAuthority.setAuthorityGrant(newUserRoleAuthority.getAuthorityGrant());
				userRoleAuthoritiesToPersist.add(userRoleAuthority);
			}
		}
		destinationUserRole.getUserRoleAuthorities().clear();
		destinationUserRole.getUserRoleAuthorities().addAll(userRoleAuthoritiesToPersist);
	}

	/**
	 * Get the Financial Year from resource.
	 *
	 * @param userRoleResource
	 * @return
	 */
	public static UserRole getUserRoleFromResource(
			final UserRoleResource userRoleResource) {
		final UserRole destinationUserRole = new UserRole();
		destinationUserRole.setResourceObjectId(userRoleResource.getObjectId());
		destinationUserRole.setRoleName(userRoleResource.getRoleName());
		destinationUserRole.setDescription(userRoleResource.getDescription());
		final Collection<UserRoleAuthority> userRoleAuthorities  = new ArrayList<UserRoleAuthority>();
		for(final String userRoleAuthorityResource :userRoleResource.getUserRoleAuthorities()){
			final UserRoleAuthority userRoleAuthority = new UserRoleAuthority();
			userRoleAuthority.setAuthorityGrant(GrantConstant.valueOf(userRoleAuthorityResource));
			userRoleAuthorities.add(userRoleAuthority);
		}
		destinationUserRole.setUserRoleAuthorities(userRoleAuthorities);
		return destinationUserRole;
	}

}
