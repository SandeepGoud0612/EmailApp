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
public class UserGroupSearchCriteria implements SearchCriteria {

	/**
	 * Universal serial version id for this class
	 */
	private static final long serialVersionUID = 8240848078901892898L;

	/**
	 * Branch.
	 */
	private Long branchId;

	private String groupName;

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
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
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
