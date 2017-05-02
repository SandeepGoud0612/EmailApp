package com.bluespacetech.security.dao;

import java.io.Serializable;
import java.util.Collection;

public class UserDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8695248104044241700L;

	private String loggedInUserName;

	private String userType;

	private Collection<String> roles;

	private Collection<String> uiRoles;

	/**
	 * @return the loggedInUserName
	 */
	public String getLoggedInUserName() {
		return loggedInUserName;
	}

	/**
	 * @param loggedInUserName
	 *            the loggedInUserName to set
	 */
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	/**
	 * @return the roles
	 */
	public Collection<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the uiRoles
	 */
	public Collection<String> getUiRoles() {
		return uiRoles;
	}

	/**
	 * @param uiRoles
	 *            the uiRoles to set
	 */
	public void setUiRoles(Collection<String> uiRoles) {
		this.uiRoles = uiRoles;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
