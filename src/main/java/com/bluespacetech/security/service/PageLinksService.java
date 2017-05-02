package com.bluespacetech.security.service;

import java.util.Set;

import com.bluespacetech.security.constants.PageLinkConstant;

public interface PageLinksService  {

	public Set<PageLinkConstant> getPageLinksAllowedForUser();
}
