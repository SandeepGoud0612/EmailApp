package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;
import com.bluespacetech.security.constants.UserAccountTypeConstant;


/**
 * Singleton class for branch expense search criteria..
 *
 * @author Pradeep
 */
public class UserAccountSearchCriteria implements SearchCriteria {

	/**
	 * Universal serial version id for this class
	 */
	private static final long serialVersionUID = -7524669904323526192L;

	/**
	 * Branch.
	 */
	private Long branchId;

	private String username;

	private boolean active;

	private boolean accountExpired;

	private boolean credentialsExpired;

	private boolean accountLocked;

	private UserAccountTypeConstant userAccountType;

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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(final boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @param credentialsExpired the credentialsExpired to set
	 */
	public void setCredentialsExpired(final boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(final boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return the userAccountType
	 */
	public UserAccountTypeConstant getUserAccountType() {
		return userAccountType;
	}

	/**
	 * @param userAccountType the userAccountType to set
	 */
	public void setUserAccountType(final UserAccountTypeConstant userAccountType) {
		this.userAccountType = userAccountType;
	}

}
