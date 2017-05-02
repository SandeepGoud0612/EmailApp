package com.bluespacetech.security.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.resources.PageLinkResource;
import com.bluespacetech.security.resources.assembler.PageLinkResourceAssembler;
import com.bluespacetech.security.service.PageLinksService;

@RestController
@RequestMapping("/pageLinks")
public class PageLinksController extends AbstractBaseController {

	@Autowired
	PageLinksService pageLinksService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PageLinkResource>> getPageLinksAllowedForUser() {

		final Set<PageLinkConstant> pageLinks = pageLinksService.getPageLinksAllowedForUser();
		final List<PageLinkResource> branchResources = new PageLinkResourceAssembler()
				.toResources(pageLinks);
		return new ResponseEntity<List<PageLinkResource>>(branchResources,
				HttpStatus.OK);
	}

}
