/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.repository;

import java.util.List;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;

/**
 * @author sandeep created date 24-Aug-2016
 */
public interface ContactRepositoryCustom {
	
	List<Contact> findContactsBySearchCriteria(ContactSearchCriteria contactSearchCriteria);

}
