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

import com.bluespacetech.security.constants.UserAccountTypeConstant;

public class UserAccountResource  extends ResourceSupport  {

	private Long objectId;

	private Long version;

	private Collection<UserAccountUserGroupResource> userAccountUserGroups;

	private String username;

	private boolean active;

	private boolean accountExpired;

	private boolean credentialsExpired;

	private boolean accountLocked;

	private UserAccountTypeConstant userAccountType;

	private String employeeNumber;

	private String password;

	private String email;

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

	/**
	 * @return the userAccountUserGroups
	 */
	public Collection<UserAccountUserGroupResource> getUserAccountUserGroups() {
		return userAccountUserGroups;
	}

	/**
	 * @param userAccountUserGroups the userAccountUserGroups to set
	 */
	public void setUserAccountUserGroups(
			final Collection<UserAccountUserGroupResource> userAccountUserGroups) {
		this.userAccountUserGroups = userAccountUserGroups;
	}

	/**
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(final String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
