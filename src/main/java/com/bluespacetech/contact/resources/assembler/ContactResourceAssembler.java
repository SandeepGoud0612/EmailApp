/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.contact.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bluespacetech.contact.controller.ContactController;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.resources.ContactResource;

/**
 * @author pradeep created date 27-Jan-2015
 */
public class ContactResourceAssembler extends ResourceAssemblerSupport<Contact, ContactResource> {

	public ContactResourceAssembler() {
		super(ContactController.class, ContactResource.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object
	 * )
	 */
	@Override
	public ContactResource toResource(final Contact contact) {
		final ContactResource contactResource = new ContactResource();
		contactResource.setObjectId(contact.getId());
		contactResource.setVersion(contact.getVersion());
		contactResource.setFirstName(contact.getFirstName());
		contactResource.setLastName(contact.getLastName());
		contactResource.setEmail(contact.getEmail());
		// contactResource.setContactGroup(contact.getContactGroup());
		final Link link = linkTo(ContactController.class).slash(contact.getId()).withSelfRel();
		contactResource.add(link.withSelfRel());
		return contactResource;
	}

	/**
	 * Copy all the properties from source Contact to destination Contact.
	 *
	 * @param sourceContact
	 *            source Contact.
	 * @param destinationContact
	 *            destination Contact.
	 */
	public static void copyContactInto(final Contact sourceContact, final Contact destinationContact) {
		destinationContact.setFirstName(sourceContact.getFirstName());
		destinationContact.setLastName(sourceContact.getLastName());
		destinationContact.setEmail(sourceContact.getEmail());
		// destinationContact.setContactGroup(sourceContact.getContactGroup());
	}

	/**
	 * Get the Financial Year from resource.
	 *
	 * @param ContactResource
	 * @return
	 */
	public static Contact getContactFromResource(final ContactResource contactResource) {
		final Contact destinationContact = new Contact();
		destinationContact.setFirstName(contactResource.getFirstName());
		destinationContact.setLastName(contactResource.getLastName());
		destinationContact.setEmail(contactResource.getEmail());
		// destinationContact.setContactGroup(contactResource.getContactGroup());
		return destinationContact;
	}

}
