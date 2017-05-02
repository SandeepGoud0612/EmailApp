/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.model;

/**
 * Entity class for user accounts
 *
 * @author Pradeep
 */
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = 1128455181710325147L;

	@Column(name = "ROLE_NAME", nullable = false, length = 40, unique = true)
	private String roleName;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	private Collection<UserRoleAuthority> userRoleAuthorities;

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
	public Collection<UserRoleAuthority> getUserRoleAuthorities() {
		return userRoleAuthorities;
	}

	/**
	 * @param userRoleAuthorities
	 *            the userRoleAuthorities to set
	 */
	public void setUserRoleAuthorities(
			final Collection<UserRoleAuthority> userRoleAuthorities) {
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

}
