/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.bluespacetech.core.constants.Labeled;

public enum PageLinkConstant implements Labeled {
	DASHBOARD("Dashboard",0, "dashboard", PageLinkTypeConstant.LINK, false, UserAccountTypeConstant.getAllUserAccountTypes(), EnumSet.of(GrantConstant.NONE)),

	//Setup Links
	REPORTS("Reports",4, "reports",PageLinkTypeConstant.TOGGLE, true, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_REPORTS)),
	SEND_EMAILS("Send Emails",1,"send_emails", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SEND_EMAIL)),
	CONTACTS("Contacts",2,"contacts", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_CONTACTS)),
	SERVERS("Servers",5,"servers", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SERVERS)),
	USER_ROLES("User Roles",6,"user_roles", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_ROLES)),
	USER_GROUPS("User Groups",7,"user_groups", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),
	USER_ACCOUNTS("User Accounts",8,"user_accounts", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),
	GROUPS("Groups",3, "groups", PageLinkTypeConstant.LINK, false,  EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN,UserAccountTypeConstant.ACC_TYPE_ADMIN,UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_GROUPS));


	private   String label;
	private final int displayOrder;
	private   Set<PageLinkConstant> childPageLinks;
	private final String pageUrl;
	private final PageLinkTypeConstant urlType;
	private final boolean branchRequired;
	private final Set<UserAccountTypeConstant> userAccountTypes;
	private final Set<GrantConstant> grants;
	private final Set<PageLinkConstant> childPageLinksAllowedForUser= new HashSet<PageLinkConstant>();

	private static final Set<PageLinkConstant> togglePages = new HashSet<PageLinkConstant>();

	PageLinkConstant(final String label,final int displayOrder, final String pageUrl,
			final PageLinkTypeConstant urlType, final boolean branchRequired,
			//final Set<PageLinkConstant> childPageLinks,
			final Set<UserAccountTypeConstant> userAccountTypes,
			final Set<GrantConstant> grants) {
		this.label = label;
		this.pageUrl=pageUrl;
		this.displayOrder = displayOrder;
		this.urlType=urlType;
		this.branchRequired=branchRequired;
		//this.childPageLinks=childPageLinks;
		this.userAccountTypes=userAccountTypes;
		this.grants=grants;
	}

	static {
		togglePages.add(SEND_EMAILS);
		togglePages.add(CONTACTS);
		togglePages.add(SERVERS);
		togglePages.add(SERVERS);
		togglePages.add(GROUPS);
		togglePages.add(USER_ROLES);
		togglePages.add(USER_GROUPS);
		togglePages.add(DASHBOARD);
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(final String label) {
		this.label = label;

	}

	/**
	 * @return the childPageLinks
	 */
	public Set<PageLinkConstant> getChildPageLinks() {
		return childPageLinks;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @return the urlType
	 */
	public PageLinkTypeConstant getUrlType() {
		return urlType;
	}

	/**
	 * @return the branchRequired
	 */
	public boolean isBranchRequired() {
		return branchRequired;
	}

	/**
	 * @return the userAccountTypes
	 */
	public Set<UserAccountTypeConstant> getUserAccountTypes() {
		return userAccountTypes;
	}

	/**
	 * @return the grants
	 */
	public Set<GrantConstant> getGrants() {
		return grants;
	}

	/**
	 * @return the togglepages
	 */
	public static Set<PageLinkConstant> getTogglepages() {
		return togglePages;
	}

	/**
	 * @return the childPageLinksAllowedForUser
	 */
	public Set<PageLinkConstant> getChildPageLinksAllowedForUser() {
		return childPageLinksAllowedForUser;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}


}
