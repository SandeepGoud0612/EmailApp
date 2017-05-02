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
import com.bluespacetech.notifications.email.service.EmailServerService;

/**
 * Rest Controller for Email Server.
 * 
 * @author sunny
 */
@RestController
@RequestMapping("/emailServer")
@CrossOrigin
public class EmailServerController {

	@Autowired
	private EmailServerService emailServerService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody final EmailServer emailServer) throws BusinessException {
		emailServerService.createEmailServer(emailServer);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailServer> update(@PathVariable final Long id, @RequestBody final EmailServer emailServer)
			throws BusinessException {
		final EmailServer currentEmailServer = emailServerService.getEmailServerById(id);
		if (currentEmailServer == null) {
			throw new BusinessException("Supplied EmailServer does not exist.");
		}
		if (!currentEmailServer.getVersion().equals(emailServer.getVersion())) {
			throw new BusinessException("Stale EmailServer. Please update.");
		}
		final EmailServer updatedEmailServer = emailServerService.updateEmailServer(emailServer);
		return new ResponseEntity<EmailServer>(updatedEmailServer, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException{
		emailServerService.deleteEmailServer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailServer> getEmailServerById(@PathVariable final Long id)throws BusinessException{
		final EmailServer emailServer = emailServerService.getEmailServerById(id);
		if (emailServer == null) {
			throw new BusinessException("Supplied EmailServer ID is invalid.");
		}
		return new ResponseEntity<EmailServer>(emailServer, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmailServer>> getAllEmailServers()throws BusinessException{
		final List<EmailServer> emailServers = emailServerService.findAll();
		return new ResponseEntity<List<EmailServer>>(emailServers,HttpStatus.OK);
	}

}
