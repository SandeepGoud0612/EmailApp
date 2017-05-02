/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Enums for Authority constants
 *
 * @author Pradeep
 */
public enum AuthorityConstant {

	DASHBOARD(GrantConstant.ACCESS_DASHBOARD, null, null, null,"Dash Board"),
	REPORTS(GrantConstant.ACCESS_REPORTS, null, null, null, "Reports"),
	SEND_EMAIL(GrantConstant.ACCESS_SEND_EMAIL, null, null, null, "Email"),
	GROUPS(GrantConstant.ACCESS_GROUPS, GrantConstant.CREATE_GROUPS, GrantConstant.UPDATE_GROUPS, GrantConstant.DELETE_GROUPS,"Email"),
	CONTACTS(GrantConstant.ACCESS_CONTACTS, GrantConstant.CREATE_CONTACTS, GrantConstant.UPDATE_CONTACTS, GrantConstant.DELETE_CONTACTS,"Email"),

	USERS(GrantConstant.ACCESS_USERS, GrantConstant.CREATE_USERS, GrantConstant.UPDATE_USERS, GrantConstant.DELETE_USERS,"User Management"),
	USER_ROLES(GrantConstant.ACCESS_USER_ROLES, GrantConstant.CREATE_USER_ROLES, GrantConstant.UPDATE_USER_ROLES, GrantConstant.DELETE_USER_ROLES,"User Management"),
	USER_GROUPS(GrantConstant.ACCESS_USER_GROUPS, GrantConstant.CREATE_USER_GROUPS, GrantConstant.UPDATE_USER_GROUPS, GrantConstant.DELETE_USER_GROUPS,"User Management");


	// User Authorities
	private GrantConstant viewGrant;
	private GrantConstant createGrant;
	private GrantConstant updateGrant;
	private GrantConstant deleteGrant;
	private String moduleName;

	AuthorityConstant(final GrantConstant viewGrant, final GrantConstant createGrant,
			final GrantConstant updateGrant, final GrantConstant deleteGrant,final String moduleName) {
		this.viewGrant = viewGrant;
		this.createGrant = createGrant;
		this.updateGrant = updateGrant;
		this.deleteGrant = deleteGrant;
		this.moduleName = moduleName;
	}

	/**
	 * @return the viewGrant
	 */
	public GrantConstant getViewGrant() {
		return viewGrant;
	}

	/**
	 * @param viewGrant
	 *            the viewGrant to set
	 */
	public void setViewGrant(final GrantConstant viewGrant) {
		this.viewGrant = viewGrant;
	}

	/**
	 * @return the createGrant
	 */
	public GrantConstant getCreateGrant() {
		return createGrant;
	}

	/**
	 * @param createGrant
	 *            the createGrant to set
	 */
	public void setCreateGrant(final GrantConstant createGrant) {
		this.createGrant = createGrant;
	}

	/**
	 * @return the updateGrant
	 */
	public GrantConstant getUpdateGrant() {
		return updateGrant;
	}

	/**
	 * @param updateGrant
	 *            the updateGrant to set
	 */
	public void setUpdateGrant(final GrantConstant updateGrant) {
		this.updateGrant = updateGrant;
	}

	/**
	 * @return the deleteGrant
	 */
	public GrantConstant getDeleteGrant() {
		return deleteGrant;
	}

	/**
	 * @param deleteGrant
	 *            the deleteGrant to set
	 */
	public void setDeleteGrant(final GrantConstant deleteGrant) {
		this.deleteGrant = deleteGrant;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	public static List<GrantConstant> getGrantsForModule(final String module){
		final List<GrantConstant> grantsForModule = new ArrayList<GrantConstant>();
		if(module!=null && module.trim().length()>0) {
			for (final AuthorityConstant authorityConstant : AuthorityConstant.values()) {
				if(authorityConstant.getModuleName().equals(module)){
					if(authorityConstant.getViewGrant()!=null){
						grantsForModule.add(authorityConstant.getViewGrant());
					}
					if(authorityConstant.getDeleteGrant()!=null){
						grantsForModule.add(authorityConstant.getDeleteGrant());
					}
					if(authorityConstant.getUpdateGrant()!=null){
						grantsForModule.add(authorityConstant.getUpdateGrant());
					}
					if(authorityConstant.getCreateGrant()!=null){
						grantsForModule.add(authorityConstant.getCreateGrant());
					}
				}
			}
		}
		return grantsForModule;
	}



}
