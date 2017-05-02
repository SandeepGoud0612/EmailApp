/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.apeironsol.com
 * Copyright © 2013 apeironsol
 *
 */
package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;

/**
 * Singleton class for branch expense search criteria..
 *
 * @author Pradeep
 */
public class UserRoleSearchCriteria implements SearchCriteria {

	/**
	 * Universal serial version id for this class
	 */
	private static final long serialVersionUID = 442834923078965556L;

	/**
	 * Branch.
	 */
	private Long branchId;

	private String roleName;

	private String description;

	/**
	 * @return the branchId
	 */
	@Override
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId
	 *            the branchId to set
	 */
	@Override
	public void setBranchId(final Long branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
