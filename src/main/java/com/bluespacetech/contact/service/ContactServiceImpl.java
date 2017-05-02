/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.repository.ContactRepositoryCustom;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * class for ContactService
 *
 * @author pradeep created date 25-Aug-2016
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
		ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactRepositoryCustom contactRepositoryCustom;

	public static void validateContact(final Contact contact) throws BusinessException {
		if (contact.getEmail() == null || contact.getEmail().trim().length() == 0) {
			throw new BusinessException("Contact Email is Mandatory.");
		}
	}

	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_CONTACTS'))")
	@Override
	public Contact createContact(final Contact contact) throws BusinessException {
		ContactServiceImpl.validateContact(contact);
		final Contact newContact = contactRepository.save(contact);
		return newContact;
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or  (hasAuthority('DELETE_CONTACTS'))")
	public void deleteContact(final Long contactId) throws BusinessException {
		contactRepository.delete(contactId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
	public List<Contact> findByFirstNameOrLastName(final String firstName, final String lastName) {
		return contactRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
	public Contact getContactById(final Long contactId) {
		return contactRepository.findOne(contactId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_CONTACTS'))")
	public Contact updateContact(final Contact contact) throws BusinessException {
		ContactServiceImpl.validateContact(contact);
		final Contact updatedContact = contactRepository.save(contact);
		return updatedContact;
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
	public List<Contact> findByEmail(final String email) {
		return contactRepository.findByEmailLike(email);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
	public List<Contact> findBySearchCriteria(ContactSearchCriteria contactSearchCriteria) {
		return contactRepositoryCustom.findContactsBySearchCriteria(contactSearchCriteria);
	}

}
