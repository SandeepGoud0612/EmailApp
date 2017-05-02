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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.security.constants.UserAccountTypeConstant;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends BaseEntity implements Serializable {

	/**
	 * Unique serial version id for this class.
	 */
	private static final long serialVersionUID = 8617426954436904583L;

	@Column(name = "USERNAME", nullable = false, length = 40, unique = true)
	private String username;

	@Column(name = "PASSWORD", nullable = false, length = 1000)
	private String password;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active;

	@Column(name = "ACC_EXPIRED", nullable = false)
	private boolean accountExpired;

	@Column(name = "CRDTLS_EXPIRED", nullable = false)
	private boolean credentialsExpired;

	@Column(name = "ACC_LOCKED", nullable = false)
	private boolean accountLocked;

	@Column(name = "USER_ACCOUNT_TYPE")
	@Enumerated(EnumType.STRING)
	private UserAccountTypeConstant userAccountType;

	@OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	private Collection<UserAccountUserGroup> userAccountUserGroups;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(final boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(final boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(final boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public UserAccountTypeConstant getUserAccountType() {
		return userAccountType;
	}

	public void setUserAccountType(final UserAccountTypeConstant userAccountType) {
		this.userAccountType = userAccountType;
	}

	/**
	 * @return the userAccountUserGroups
	 */
	public Collection<UserAccountUserGroup> getUserAccountUserGroups() {
		return userAccountUserGroups;
	}

	/**
	 * @param userAccountUserGroups
	 *            the userAccountUserGroups to set
	 */
	public void setUserAccountUserGroups(
			final Collection<UserAccountUserGroup> userAccountUserGroups) {
		this.userAccountUserGroups = userAccountUserGroups;
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
