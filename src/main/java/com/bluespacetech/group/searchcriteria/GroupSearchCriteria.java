/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.group.searchcriteria;

import java.io.Serializable;

/**
 * @author sandeep created date 24-Aug-2016
 */
public class GroupSearchCriteria implements Serializable{
	
	private static final long serialVersionUID = 4218813103611029021L;

	private String name;
	
	private String comments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
