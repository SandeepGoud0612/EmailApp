/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.controller.PageLinksController;
import com.bluespacetech.security.resources.PageLinkResource;

/**
 * @author pradeep created date 27-Jan-2015
 */
public class PageLinkResourceAssembler extends
ResourceAssemblerSupport<PageLinkConstant, PageLinkResource> {

	public PageLinkResourceAssembler() {
		super(PageLinksController.class, PageLinkResource.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object
	 * )
	 */
	@Override
	public PageLinkResource toResource(final PageLinkConstant pageLinkConstant) {
		final PageLinkResource pageLinkResource = new PageLinkResource();
		pageLinkResource.setName(pageLinkConstant.toString());
		pageLinkResource.setLabel(pageLinkConstant.getLabel());
		pageLinkResource.setSelectBranchRequired(pageLinkConstant.isBranchRequired());
		pageLinkResource.setType(pageLinkConstant.getUrlType().getLabel());
		pageLinkResource.setUrl(pageLinkConstant.getPageUrl());
		pageLinkResource.setParentDisplayOrder(pageLinkConstant.getDisplayOrder());

		final List<PageLinkResource> children = new ArrayList<PageLinkResource>();
		for(final PageLinkConstant childPageLink : pageLinkConstant.getChildPageLinksAllowedForUser()){
			final PageLinkResource childPageLinkResource = new PageLinkResource();
			childPageLinkResource.setName(childPageLink.toString());
			childPageLinkResource.setSelectBranchRequired(childPageLink.isBranchRequired());
			childPageLinkResource.setType(childPageLink.getUrlType().getLabel());
			childPageLinkResource.setUrl(childPageLink.getPageUrl());
			childPageLinkResource.setChildDisplayOrder(childPageLink.getDisplayOrder());
			children.add(childPageLinkResource);
		}
		if(children.size()>0) {
			pageLinkResource.setPages(children);
		}
		return pageLinkResource;
	}

}
