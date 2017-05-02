/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author pradeep created date 30-Jan-2015
 */
public class PageLinkResource extends ResourceSupport {

	private String name;
	private String label;
	private int parentDisplayOrder;
	private int childDisplayOrder;
	private List<PageLinkResource> pages;
	private String url;
	private String type;
	private boolean selectBranchRequired;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @return the pages
	 */
	public List<PageLinkResource> getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(final List<PageLinkResource> pages) {
		this.pages = pages;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}
	/**
	 * @return the selectBranchRequired
	 */
	public boolean isSelectBranchRequired() {
		return selectBranchRequired;
	}
	/**
	 * @param selectBranchRequired the selectBranchRequired to set
	 */
	public void setSelectBranchRequired(final boolean selectBranchRequired) {
		this.selectBranchRequired = selectBranchRequired;
	}
	/**
	 * @return the parentDisplayOrder
	 */
	public int getParentDisplayOrder() {
		return parentDisplayOrder;
	}
	/**
	 * @param parentDisplayOrder the parentDisplayOrder to set
	 */
	public void setParentDisplayOrder(final int parentDisplayOrder) {
		this.parentDisplayOrder = parentDisplayOrder;
	}
	/**
	 * @return the childDisplayOrder
	 */
	public int getChildDisplayOrder() {
		return childDisplayOrder;
	}
	/**
	 * @param childDisplayOrder the childDisplayOrder to set
	 */
	public void setChildDisplayOrder(final int childDisplayOrder) {
		this.childDisplayOrder = childDisplayOrder;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
