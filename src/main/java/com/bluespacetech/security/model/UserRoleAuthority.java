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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.security.constants.GrantConstant;

@Entity
@Table(name = "USER_ROLE_AUTHORITY")
public class UserRoleAuthority extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -6708597673610237653L;

	@Column(name = "AUTH_GRANT", nullable = false, length = 100)
	@Enumerated(EnumType.STRING)
	private GrantConstant authorityGrant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ROLE_ID")
	private UserRole userRole;

	/**
	 * @return the authorityGrant
	 */
	public GrantConstant getAuthorityGrant() {
		return authorityGrant;
	}

	/**
	 * @param authorityGrant
	 *            the authorityGrant to set
	 */
	public void setAuthorityGrant(final GrantConstant authorityGrant) {
		this.authorityGrant = authorityGrant;
	}

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public void setUserRole(final UserRole userRole) {
		this.userRole = userRole;
	}

}
