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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

@Entity
@Table(name = "USER_GROUP_USER_ROLE")
public class UserGroupUserRole extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -974709751016922398L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_GROUP_ID")
	private UserGroup userGroup;

	@Column(name = "USER_ROLE_ID", nullable = false)
	private Long userRoleId;

	/**
	 * @return the userGroup
	 */
	public UserGroup getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(final UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @return the userRoleId
	 */
	public Long getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(final Long userRoleId) {
		this.userRoleId = userRoleId;
	}

}
