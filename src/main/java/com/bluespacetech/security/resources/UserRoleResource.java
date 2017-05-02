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

public class UserRoleResource  extends ResourceSupport  {


	private Long objectId;

	private Long version;

	private String roleName;

	private String description;

	private Collection<String> userRoleAuthorities;

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the userRoleAuthorities
	 */
	public Collection<String> getUserRoleAuthorities() {
		return userRoleAuthorities;
	}

	/**
	 * @param userRoleAuthorities
	 *            the userRoleAuthorities to set
	 */
	public void setUserRoleAuthorities(
			final Collection<String> userRoleAuthorities) {
		this.userRoleAuthorities = userRoleAuthorities;
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

}
