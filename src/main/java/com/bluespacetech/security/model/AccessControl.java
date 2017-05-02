/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.model;

/**
 * class for security role
 *
 * @author Pradeep
 */
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

@Entity
@Table(name = "ACCESS_CONTROL")
public class AccessControl extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -927045001321641282L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
	private UserAccount userAccount;

	@Column(name = "BRANCH_ID", nullable = false)
	private Long branchId;

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/**
	 * @return the branchId
	 */
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId
	 *            the branchId to set
	 */
	public void setBranchId(final Long branchId) {
		this.branchId = branchId;
	}

}
