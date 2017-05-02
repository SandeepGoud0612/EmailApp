/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluespacetech.security.constants.UserAccountTypeConstant;

public final class ViewUtil {

	public static String getPrincipal() {
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static UserAccountTypeConstant getUserAccountType() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccountTypeConstant userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;
		if (authentication != null && authentication.getAuthorities() != null) {
			for (final GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN;
				} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
				} else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
				} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
				}
			}
		}
		return userAccountType;
	}

}
