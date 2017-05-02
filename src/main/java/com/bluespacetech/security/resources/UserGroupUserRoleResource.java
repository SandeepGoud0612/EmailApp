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
import org.springframework.hateoas.ResourceSupport;

public class UserGroupUserRoleResource  extends ResourceSupport  {

	private Long objectId;

	private Long version;

	private UserRoleResource userRole;

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
	 * @return the userRole
	 */
	public UserRoleResource getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(final UserRoleResource userRole) {
		this.userRole = userRole;
	}

}
