/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * Entity class for Branch.
 *
 * @author Pradeep
 */
@Entity
@Table(name = "USER_PREFERENCE")
public class UserPreference extends BaseEntity {

	/**
	 * Universal serial version number.
	 */
	private static final long serialVersionUID = 6630017830597146593L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
	private UserAccount userAccount;

	@Column(name = "DEFAULT_BRANCH_ID")
	private Long branchId;

	@Column(name = "DEC_PERCENTAGE_IND")
	private boolean decreasePercentageInd;

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

	/**
	 * @return the decreasePercentageInd
	 */
	public boolean isDecreasePercentageInd() {
		return decreasePercentageInd;
	}

	/**
	 * @param decreasePercentageInd the decreasePercentageInd to set
	 */
	public void setDecreasePercentageInd(final boolean decreasePercentageInd) {
		this.decreasePercentageInd = decreasePercentageInd;
	}

}
