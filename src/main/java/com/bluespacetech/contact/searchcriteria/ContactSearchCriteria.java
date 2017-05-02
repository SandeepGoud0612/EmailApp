/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.searchcriteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sandeep created date 24-Aug-2016
 */
public class ContactSearchCriteria  implements Serializable{
	
	private static final long serialVersionUID = -8455110031782999764L;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<Long> groupIds = new ArrayList<>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}
	
}
