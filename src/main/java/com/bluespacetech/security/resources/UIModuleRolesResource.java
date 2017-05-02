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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class UIModuleRolesResource  extends ResourceSupport  {

	private Collection<String> uiUserRoles;

	private String moduleName;

	private final List<String> moduleNames = new ArrayList<String>();

	/**
	 * @return the uiUserRoles
	 */
	public Collection<String> getUiUserRoles() {
		return uiUserRoles;
	}

	/**
	 * @param uiUserRoles the uiUserRoles to set
	 */
	public void setUiUserRoles(final Collection<String> uiUserRoles) {
		this.uiUserRoles = uiUserRoles;
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

	/**
	 * @return the moduleNames
	 */
	public List<String> getModuleNames() {
		return moduleNames;
	}

}
