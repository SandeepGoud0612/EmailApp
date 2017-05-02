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
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

@Entity
@Table(name = "USER_GROUP")
public class UserGroup extends BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = 1128455181710325147L;

	@Column(name = "GROUP_NAME", nullable = false, length = 40, unique = true)
	private String groupName;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	private Collection<UserGroupUserRole> userGroupUserRoles;


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the userGroupUserRolee
	 */
	public Collection<UserGroupUserRole> getUserGroupUserRoles() {
		return userGroupUserRoles;
	}

	/**
	 * @param userGroupUserRolee
	 *            the userGroupUserRolee to set
	 */
	public void setUserGroupUserRole(
			final Collection<UserGroupUserRole> userGroupUserRoles) {
		this.userGroupUserRoles = userGroupUserRoles;
	}

}
