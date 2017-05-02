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
@Table(name = "USER_ACCOUNT_USER_GROUP")
public class UserAccountUserGroup extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -7235237614145212463L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ACCOUNT_ID")
	private UserAccount userAccount;

	@Column(name = "USER_GROUP_ID", nullable = false)
	private Long userGroupId;

	/**
	 * @return the userAccount
	 */
	public UserAccount getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the userGroup
	 */
	public Long getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroupId(final Long userGroup) {
		this.userGroupId = userGroup;
	}

}
