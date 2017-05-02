/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contactgroup.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.repository.ContactGroupRepositoryCustom;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * @author sandeep created date 25-Aug-2015
 */
@RestController
@RequestMapping("/contactgroups")
@CrossOrigin
public class ContactGroupController {
	
	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactGroupService contactGroupService;

	@Autowired
	private ContactGroupRepositoryCustom contactGroupRepositoryCustom;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ContactGroup>> getContactGroups() {
		final List<ContactGroup> contactGroups = contactGroupService.findAll();
		return new ResponseEntity<List<ContactGroup>>(contactGroups, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody final ContactGroup contactGroup) throws BusinessException {
		Contact contact = contactGroup.getContact();
		contactService.updateContact(contact);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{contactId}/{groupId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long contactId, @PathVariable final Long groupId)
			throws BusinessException {
		ContactGroup contactGroup = contactGroupRepositoryCustom.getContactGroupByContactIdAndGroupId(contactId,
				groupId);
		contactGroupService.deleteContactGroup(contactGroup.getContactGroupPK());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
