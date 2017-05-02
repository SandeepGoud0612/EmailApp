/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.service.GroupService;

/**
 * @author pradeep created date 30-Jan-2015
 */
@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController {

	@Autowired
	ContactService contactService;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	GroupService groupService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody final Contact contact) throws BusinessException {
		for (final ContactGroup contactGroup : contact.getContactGroups()) {
			contactGroup.setContact(contact);
			contactGroup.getGroup().setContactGroups(contact.getContactGroups());
		}
		contactService.createContact(contact);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> update(@PathVariable final Long id, @RequestBody final Contact contact) throws BusinessException {

		final Contact currentContact = contactService.getContactById(id);
		if (currentContact == null) {
			throw new BusinessException("Supplied Contact does not exist.");
		}
		if (!currentContact.getVersion().equals(contact.getVersion())) {
			throw new BusinessException("Stale Contact. Please update.");
		}

		for (final ContactGroup contactGroup : contact.getContactGroups()) {
			contactGroup.setContact(contact);
			contactGroup.getGroup().setContactGroups(contact.getContactGroups());
		}

		final Contact contactUpdated = contactService.updateContact(contact);
		contactUpdated.getContactGroups().stream().forEach(contactGroup -> {
			contactGroup.setContact(null);
		});
		return new ResponseEntity<Contact>(contactUpdated, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> getContactById(@PathVariable final Long id) throws BusinessException {
		final Contact contact = contactService.getContactById(id);
		if (contact == null) {
			throw new BusinessException("Supplied Contact ID is invalid.");
		}
		contact.getContactGroups().stream().forEach(contactGroup -> {
			contactGroup.setContact(null);
		});
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> getContacts() {
		final List<Contact> contacts = contactService.findAll();
		contacts.stream().forEach(contact -> {
			contact.getContactGroups().stream().forEach(contactGroup -> {
				contactGroup.setContact(null);
			});
		});
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "/searchCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> getContactsBySearchCriteria(@RequestBody final ContactSearchCriteria contactSearchCriteria) {
		if (contactSearchCriteria.getFirstName() != null) {
			contactSearchCriteria.setFirstName(contactSearchCriteria.getFirstName().trim());
			if (contactSearchCriteria.getFirstName().trim().equals("")) {
				contactSearchCriteria.setFirstName(null);
			}
		}
		if (contactSearchCriteria.getLastName() != null) {
			contactSearchCriteria.setLastName(contactSearchCriteria.getLastName().trim());
			if (contactSearchCriteria.getLastName().trim().equals("")) {
				contactSearchCriteria.setLastName(null);
			}
		}
		if (contactSearchCriteria.getEmail() != null) {
			contactSearchCriteria.setEmail(contactSearchCriteria.getEmail().trim());
			if (contactSearchCriteria.getEmail().trim().equals("")) {
				contactSearchCriteria.setEmail(null);
			}
		}
		final List<Contact> contacts = contactService.findBySearchCriteria(contactSearchCriteria);
		contacts.stream().forEach(contact -> {
			contact.setCreationDate(null);
			contact.setLastUpdatedDate(null);
			contact.setCreatedUser(null);
			contact.setLastUpdatedUser(null);
			contact.setResourceObjectId(null);
			contact.getContactGroups().stream().forEach(contactGroup -> {
				contactGroup.setContact(null);
				contactGroup.getGroup().setCreatedUser(null);
				contactGroup.getGroup().setLastUpdatedDate(null);
				contactGroup.getGroup().setCreatedUser(null);
				contactGroup.getGroup().setLastUpdatedUser(null);
				contactGroup.getGroup().setComments(null);
			});
		});
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException {
		contactService.deleteContact(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<String> handleContactNotFoundException(final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/createContacts", method = RequestMethod.GET)
	public ResponseEntity<Void> createContacts() throws BusinessException {

		final ArrayList<Contact> contacts = new ArrayList<Contact>();
		for (Long i = 0L; i < 5000L; i++) {
			final Contact contact1 = new Contact();
			contact1.setFirstName("contact" + i);
			contact1.setLastName("lastname" + i);
			contact1.setEmail("kpgoud533@gmail.com");

			final Group group1 = groupService.getGroupById(1L);

			final ContactGroup contactGroup1 = new ContactGroup();
			contactGroup1.setContact(contact1);
			contactGroup1.setGroup(group1);
			contactGroup1.setActive(true);
			contactGroup1.setUnSubscribed(false);

			contact1.getContactGroups().add(contactGroup1);

			contacts.add(contact1);
			if (i % 1000 == 0) {
				contactRepository.save(contacts);
				contacts.clear();
			}

		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
