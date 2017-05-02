/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Enums for Authority constants
 *
 * @author Pradeep
 */
public enum UserAccountTypeConstant {

	ACC_TYPE_SUPER_ADMIN("ACC_TYPE_SUPER_ADMIN"), ACC_TYPE_ADMIN(
			"ACC_TYPE_ADMIN"), ACC_TYPE_EMPLOYEE("ACC_TYPE_EMPLOYEE"), ACC_TYPE_USER("ACC_TYPE_USER"),;

	// User Authorities
	private String accountType;

	UserAccountTypeConstant(final String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	public static Set<UserAccountTypeConstant> getAllUserAccountTypes(){
		return new HashSet<UserAccountTypeConstant>(Arrays.asList(UserAccountTypeConstant.values()));
	}


	public static UserAccountTypeConstant[] getUserAccountTypesForNewUser(){
		final UserAccountTypeConstant[] userAccountTypesForNewUser = new UserAccountTypeConstant[2];
		userAccountTypesForNewUser[0] = UserAccountTypeConstant.ACC_TYPE_ADMIN;
		userAccountTypesForNewUser[1] = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
		//userAccountTypesForNewUser[5] = UserAccountTypeConstant.ACC_TYPE_TEACHER;
		//userAccountTypesForNewUser[2] = UserAccountTypeConstant.ACC_TYPE_STUDENT;
		//userAccountTypesForNewUser[3] = UserAccountTypeConstant.ACC_TYPE_PARENT;
		//userAccountTypesForNewUser[4] = UserAccountTypeConstant.ACC_TYPE_USER;

		return userAccountTypesForNewUser;
	}



}
