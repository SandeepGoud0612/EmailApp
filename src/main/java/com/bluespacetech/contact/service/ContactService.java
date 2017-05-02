/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contact.service;

import java.util.List;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * class for ContactService
 *
 * @author pradeep created date 25-June-2015
 */
public interface ContactService {

    Contact createContact(final Contact contact) throws BusinessException;

    void deleteContact(final Long contactId) throws BusinessException;

    List<Contact> findByFirstNameOrLastName(final String firstName,final String lastName);

    List<Contact> findByEmail(final String email);

    List<Contact> findAll();

    Contact getContactById(final Long contactId);

    Contact updateContact(final Contact contact) throws BusinessException;
    
    List<Contact> findBySearchCriteria(final ContactSearchCriteria contactSearchCriteria);
}
