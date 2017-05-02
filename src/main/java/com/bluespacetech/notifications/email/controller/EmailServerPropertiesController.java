/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;

/**
 * Rest Controller for Email Server.
 * 
 * @author sunny
 */
@RestController
@RequestMapping("/emailServerProperties")
@CrossOrigin
public class EmailServerPropertiesController {

	@Autowired
	private EmailServerService emailServerService;

	@Autowired
	private EmailServerPropertiesService emailServerPropertiesService;

	@RequestMapping(value = "/{emailServerId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailServerProperties> create(@PathVariable final Long emailServerId,
			@RequestBody final EmailServerProperties emailServerProperties) throws BusinessException {
		final EmailServer emailServer = emailServerService.getEmailServerById(emailServerId);
		if (emailServer == null) {
			throw new BusinessException("Supplied EmailServer does not exist.");
		}
		emailServerProperties.setEmailServer(emailServer);
		final EmailServerProperties updatedEmailServerProperties = emailServerPropertiesService
				.createEmailServerProperty(emailServerProperties);
		return new ResponseEntity<EmailServerProperties>(updatedEmailServerProperties, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{emailServerId}/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailServerProperties> update(@PathVariable final Long emailServerId,
			@PathVariable final Long id,
			@RequestBody final EmailServerProperties emailServerProperties)
					throws BusinessException {
		final EmailServer emailServer = emailServerService.getEmailServerById(emailServerId);
		if (emailServer == null) {
			throw new BusinessException("Supplied EmailServer does not exist.");
		}
		emailServerProperties.setEmailServer(emailServer);
		final EmailServerProperties createEmailServerProperties = emailServerPropertiesService
				.getEmailServerPropertiesById(id);
		if (createEmailServerProperties == null) {
			throw new BusinessException("Supplied EmailServerProperties does not exist.");
		}

		if (!emailServerProperties.getVersion().equals(createEmailServerProperties.getVersion())) {
			throw new BusinessException("Stale EmailServerProperties. Please update.");
		}
		final EmailServerProperties updatedEmailServerProperties = emailServerPropertiesService
				.updateEmailServerProperty(emailServerProperties);
		return new ResponseEntity<EmailServerProperties>(updatedEmailServerProperties, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException{
		emailServerPropertiesService.deleteEmailServerProperty(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailServerProperties> getEmailServerById(@PathVariable final Long id)
			throws BusinessException {
		final EmailServerProperties emailServerProperties = emailServerPropertiesService
				.getEmailServerPropertiesById(id);
		if (emailServerProperties == null) {
			throw new BusinessException("Supplied EmailServerProperties ID is invalid.");
		}
		return new ResponseEntity<EmailServerProperties>(emailServerProperties, HttpStatus.OK);
	}

	@RequestMapping(value = "/emailServer/{emailServerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmailServerProperties>> getAllEmailServerProperties(
			@PathVariable final Long emailServerId)
					throws BusinessException {
		final EmailServer emailServer = emailServerService.getEmailServerById(emailServerId);
		if (emailServer == null) {
			throw new BusinessException("Supplied EmailServer does not exist.");
		}
		final List<EmailServerProperties> emailServerProperties = emailServerPropertiesService
				.findByEmailServer(emailServer);
		return new ResponseEntity<List<EmailServerProperties>>(emailServerProperties, HttpStatus.OK);
	}

}
