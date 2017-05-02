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

public class UserAccountUserGroupResource  extends ResourceSupport  {

	private Long objectId;

	private Long version;

	private UserGroupResource userGroup;

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
	 * @return the userGroup
	 */
	public UserGroupResource getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup the userGroup to set
	 */
	public void setUserGroup(final UserGroupResource userGroup) {
		this.userGroup = userGroup;
	}

}
