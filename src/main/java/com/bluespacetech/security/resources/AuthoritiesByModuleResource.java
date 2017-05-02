/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author pradeep created date 30-Jan-2015
 */
public class AuthoritiesByModuleResource extends ResourceSupport {

	private String moduleName;

	private List<AuthorityResource> authorityResources = new ArrayList<AuthorityResource>();

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
	/**
	 * @return the authorityResources
	 */
	public List<AuthorityResource> getAuthorityResources() {
		return authorityResources;
	}
	/**
	 * @param authorityResources the authorityResources to set
	 */
	public void setAuthorityResources(final List<AuthorityResource> authorityResources) {
		this.authorityResources = authorityResources;
	}
}
