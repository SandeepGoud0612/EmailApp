/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.contact.resources;

import org.springframework.hateoas.ResourceSupport;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;

/**
 * @author pradeep created date 30-Jan-2015
 */
public class ContactResource extends ResourceSupport {

	private Long objectId;

	private Long version;

	private String				firstName;

	private String				lastName;

	private String				email;

	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setContactGroup(Group group) {
		this.group = group;
	}

	/**
	 * @return the objectId
	 */
	public Long getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(final Long objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final Long version) {
		this.version = version;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	public static ContactResource convertToPeronResource(final Contact contact, final boolean completeResource,
			final boolean viewContactInfoPermission, final boolean viewAddressPermission) {
		final ContactResource contactResource = new ContactResource();
		contactResource.setFirstName(contact.getFirstName());
		contactResource.setLastName(contact.getLastName());
		contactResource.setObjectId(contact.getId());
		contactResource.setVersion(contact.getVersion());
		contactResource.setEmail(contact.getEmail());
		return contactResource;
	}

	public static Contact getContactFromResource(final ContactResource contactResource) {
		final Contact destinationContact = new Contact();
		destinationContact.setFirstName(contactResource.getFirstName());
		destinationContact.setLastName(contactResource.getLastName());
		destinationContact.setEmail(contactResource.getEmail());
		return destinationContact;
	}

	public static void copyContactInto(final Contact sourceContact, final Contact destinationContact) {
		destinationContact.setFirstName(sourceContact.getFirstName());
		destinationContact.setLastName(sourceContact.getLastName());
		destinationContact.setEmail(sourceContact.getEmail());
	}
}
