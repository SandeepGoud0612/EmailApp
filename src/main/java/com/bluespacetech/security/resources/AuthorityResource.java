/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources;

import org.springframework.hateoas.ResourceSupport;

import com.bluespacetech.security.constants.AuthorityConstant;
import com.bluespacetech.security.constants.GrantConstant;

/**
 * @author pradeep created date 30-Jan-2015
 */
public class AuthorityResource extends ResourceSupport {

	private AuthorityConstant authorityConstant;

	private GrantConstant viewGrant;
	private GrantConstant createGrant;
	private GrantConstant updateGrant;
	private GrantConstant deleteGrant;
	private String moduleName;

	/**
	 * @return the authorityConstant
	 */
	public AuthorityConstant getAuthorityConstant() {
		return authorityConstant;
	}
	/**
	 * @param authorityConstant the authorityConstant to set
	 */
	public void setAuthorityConstant(final AuthorityConstant authorityConstant) {
		this.authorityConstant = authorityConstant;
	}
	/**
	 * @return the viewGrant
	 */
	public GrantConstant getViewGrant() {
		return viewGrant;
	}
	/**
	 * @param viewGrant the viewGrant to set
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
	 * @param createGrant the createGrant to set
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
	 * @param updateGrant the updateGrant to set
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
	 * @param deleteGrant the deleteGrant to set
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

	public static AuthorityResource getAuthorityResource(final AuthorityConstant authorityConstant){
		final AuthorityResource authorityResource = new AuthorityResource();
		authorityResource.setAuthorityConstant(authorityConstant);
		authorityResource.setViewGrant(authorityConstant.getViewGrant());
		authorityResource.setCreateGrant(authorityConstant.getCreateGrant());
		authorityResource.setUpdateGrant(authorityConstant.getUpdateGrant());
		authorityResource.setDeleteGrant(authorityConstant.getDeleteGrant());
		authorityResource.setModuleName(authorityConstant.getModuleName());
		return authorityResource;
	}



}
