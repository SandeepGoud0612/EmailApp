/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources;

/**
 * Entity class for user accounts
 *
 * @author Pradeep
 */
import java.util.Collection;

import org.springframework.hateoas.ResourceSupport;

public class UserGroupResource  extends ResourceSupport  {

	private Long objectId;

	private Long version;

	private String groupName;

	private String description;

	private Collection<UserGroupUserRoleResource> userGroupUserRoles;

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the objectId
	 */
	public Long getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(final Long objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final Long version) {
		this.version = version;
	}

	/**
	 * @return the userGroupUserRoles
	 */
	public Collection<UserGroupUserRoleResource> getUserGroupUserRoles() {
		return userGroupUserRoles;
	}

	/**
	 * @param userGroupUserRoles the userGroupUserRoles to set
	 */
	public void setUserGroupUserRoles(
			final Collection<UserGroupUserRoleResource> userGroupUserRoles) {
		this.userGroupUserRoles = userGroupUserRoles;
	}

}
